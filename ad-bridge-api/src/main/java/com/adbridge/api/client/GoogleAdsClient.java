package com.adbridge.api.client;

import com.adbridge.api.config.AdPlatformClientsConfiguration.GoogleConfiguration;
import com.adbridge.model.Campaign;
import com.adbridge.model.Platform;
import com.adbridge.model.PlatformCampaign;
import java.util.UUID;

public class GoogleAdsClient implements AdPlatformClient {

    private final GoogleConfiguration configuration;

    public GoogleAdsClient(GoogleConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public PlatformCampaign createCampaign(Campaign campaign) {
        String campaignId = "google-cmp-" + UUID.randomUUID();
        String adId = "google-ad-" + UUID.randomUUID();

        String response = "stubbed Google Ads API response (customer=" + configuration.getCustomerId() + ")";

        return PlatformCampaign.builder()
                .platform(Platform.GOOGLE)
                .externalCampaignId(campaignId)
                .externalAdId(adId)
                .rawResponse(response)
                .build();
    }
}