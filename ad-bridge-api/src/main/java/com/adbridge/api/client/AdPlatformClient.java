package com.adbridge.api.client;

import com.adbridge.model.Campaign;
import com.adbridge.model.PlatformCampaign;

public interface AdPlatformClient {
    PlatformCampaign createCampaign(Campaign campaign);
}