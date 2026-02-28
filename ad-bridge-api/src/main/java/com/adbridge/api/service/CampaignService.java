package com.adbridge.api.service;

import com.adbridge.model.Campaign;
import com.adbridge.model.OptimizeResponse;

public interface CampaignService {
    Campaign createCampaign(Campaign campaign);

    Campaign getCampaign(String campaignId);

    Campaign syncInsights(String campaignId);

    OptimizeResponse optimizeCampaign(String campaignId, boolean apply);
}
