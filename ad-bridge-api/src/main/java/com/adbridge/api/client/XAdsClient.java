package com.adbridge.api.client;

import com.adbridge.api.config.AdPlatformClientsConfiguration.XConfiguration;
import com.adbridge.model.Campaign;
import com.adbridge.model.Platform;
import com.adbridge.model.PlatformCampaign;
import java.util.UUID;

public class XAdsClient implements AdPlatformClient {

    private final XConfiguration configuration;

    public XAdsClient(XConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public PlatformCampaign createCampaign(Campaign campaign) {
        String campaignId = "x-cmp-" + UUID.randomUUID();
        String adId = "x-ad-" + UUID.randomUUID();

        String response = "stubbed X Ads API response (clientId=" + configuration.getClientId() + ")";

        return PlatformCampaign.builder()
                .platform(Platform.X)
                .externalCampaignId(campaignId)
                .externalAdId(adId)
                .rawResponse(response)
                .build();
    }
}