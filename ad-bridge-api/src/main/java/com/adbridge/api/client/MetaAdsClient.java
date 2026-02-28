package com.adbridge.api.client;

import com.adbridge.api.config.AdPlatformClientsConfiguration.MetaConfiguration;
import com.adbridge.model.Campaign;
import com.adbridge.model.Platform;
import com.adbridge.model.PlatformCampaign;
import java.util.UUID;

public class MetaAdsClient implements AdPlatformClient {

    private final MetaConfiguration configuration;

    public MetaAdsClient(MetaConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public PlatformCampaign createCampaign(Campaign campaign) {
        String campaignId = "meta-cmp-" + UUID.randomUUID();
        String adsetId = "meta-adset-" + UUID.randomUUID();
        String adId = "meta-ad-" + UUID.randomUUID();

        String response = "stubbed Meta API response (account=" + configuration.getAdAccountId() + ")";

        return PlatformCampaign.builder()
                .platform(Platform.META)
                .externalCampaignId(campaignId)
                .externalAdsetId(adsetId)
                .externalAdId(adId)
                .rawResponse(response)
                .build();
    }
}