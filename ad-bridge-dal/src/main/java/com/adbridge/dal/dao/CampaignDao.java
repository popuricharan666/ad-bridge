package com.adbridge.dal.dao;

import com.adbridge.model.Campaign;

public interface CampaignDao {
    Campaign save(Campaign campaign);

    Campaign findById(String campaignId);
}
