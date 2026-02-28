package com.adbridge.api.service;

import com.adbridge.api.client.AdPlatformClient;
import com.adbridge.api.client.AdPlatformClientRegistry;
import com.google.inject.Inject;
import com.adbridge.dal.dao.CampaignDao;
import com.adbridge.model.Campaign;
import com.adbridge.model.InsightsSnapshot;
import com.adbridge.model.OptimizationAction;
import com.adbridge.model.OptimizeResponse;
import com.adbridge.model.Platform;
import com.adbridge.model.PlatformCampaign;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CampaignServiceImpl implements CampaignService {

    private final CampaignDao campaignDao;
    private final AdPlatformClientRegistry clientRegistry;

    @Override
    public Campaign createCampaign(Campaign campaign) {
        campaign.setId(UUID.randomUUID().toString());
        campaign.setCreatedAt(Instant.now());
        campaign.setUpdatedAt(Instant.now());

        List<PlatformCampaign> platformCampaigns = new ArrayList<>();
        for (Platform platform : campaign.getPlatforms()) {
            AdPlatformClient client = clientRegistry.getClient(platform);
            if (client == null) {
                PlatformCampaign fallback = PlatformCampaign.builder()
                        .platform(platform)
                        .externalCampaignId(platform.name().toLowerCase() + "-cmp-" + campaign.getId())
                        .externalAdId(platform.name().toLowerCase() + "-ad-" + campaign.getId())
                        .externalAdsetId(platform == Platform.META ? "meta-adset-" + campaign.getId() : null)
                        .rawResponse("no client configured for " + platform)
                        .build();
                platformCampaigns.add(fallback);
                continue;
            }

            PlatformCampaign pc = client.createCampaign(campaign);
            platformCampaigns.add(pc);
        }
        campaign.setPlatformCampaigns(platformCampaigns);

        return campaignDao.save(campaign);
    }

    @Override
    public Campaign getCampaign(String campaignId) {
        return campaignDao.findById(campaignId);
    }

    @Override
    public Campaign syncInsights(String campaignId) {
        Campaign campaign = campaignDao.findById(campaignId);
        if (campaign == null) {
            return null;
        }

        List<InsightsSnapshot> snapshots = new ArrayList<>();
        for (Platform platform : campaign.getPlatforms()) {
            snapshots.add(buildInsights(platform));
        }

        campaign.getInsights().addAll(snapshots);
        campaign.setUpdatedAt(Instant.now());
        return campaignDao.save(campaign);
    }

    @Override
    public OptimizeResponse optimizeCampaign(String campaignId, boolean apply) {
        Campaign campaign = campaignDao.findById(campaignId);
        if (campaign == null) {
            return new OptimizeResponse(null, List.of());
        }

        List<OptimizationAction> actions = new ArrayList<>();

        for (Platform platform : campaign.getPlatforms()) {
            InsightsSnapshot latest = getLatestSnapshot(campaign, platform);
            if (latest == null) {
                continue;
            }

            if (latest.getCtr() < 0.01) {
                List<String> interests = new ArrayList<>(campaign.getInterests());
                interests.add("online shoppers");
                List<String> uniqueInterests = new ArrayList<>(new LinkedHashSet<>(interests));
                int updatedAgeMin = Math.max((campaign.getAgeMin() == null ? 18 : campaign.getAgeMin()) - 2, 18);

                OptimizationAction action = OptimizationAction.builder()
                        .platform(platform)
                        .action("update_targeting")
                        .reason("Low CTR detected (" + String.format("%.4f", latest.getCtr()) + ")")
                        .changes(Map.of("interests", uniqueInterests, "ageMin", updatedAgeMin))
                        .build();
                actions.add(action);
            }
        }

        if (apply && !actions.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<String> updatedInterests = (List<String>) actions.get(0).getChanges().get("interests");
            int updatedAgeMin = (Integer) actions.get(0).getChanges().get("ageMin");

            campaign.setInterests(updatedInterests);
            campaign.setAgeMin(updatedAgeMin);
            campaign.setUpdatedAt(Instant.now());
            campaign = campaignDao.save(campaign);
        }

        return new OptimizeResponse(campaign, actions);
    }

    private InsightsSnapshot getLatestSnapshot(Campaign campaign, Platform platform) {
        InsightsSnapshot latest = null;
        for (InsightsSnapshot snapshot : campaign.getInsights()) {
            if (snapshot.getPlatform() == platform) {
                latest = snapshot;
            }
        }
        return latest;
    }

    private InsightsSnapshot buildInsights(Platform platform) {
        int impressions;
        int clicks;
        double spend;

        if (platform == Platform.META) {
            impressions = 42000;
            clicks = 620;
            spend = 340.0;
        } else if (platform == Platform.GOOGLE) {
            impressions = 53000;
            clicks = 510;
            spend = 450.0;
        } else {
            impressions = 25000;
            clicks = 190;
            spend = 130.0;
        }

        return InsightsSnapshot.builder()
                .platform(platform)
                .impressions(impressions)
                .clicks(clicks)
                .spend(spend)
                .ctr((double) clicks / impressions)
                .cpc(spend / clicks)
                .capturedAt(Instant.now())
                .build();
    }
}
