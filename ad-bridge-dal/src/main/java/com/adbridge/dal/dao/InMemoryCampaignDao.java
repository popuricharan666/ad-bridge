package com.adbridge.dal.dao;

import com.adbridge.model.Campaign;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InMemoryCampaignDao implements CampaignDao {
    private final Map<String, Campaign> store = new ConcurrentHashMap<>();

    @Override
    public Campaign save(Campaign campaign) {
        store.put(campaign.getId(), campaign);
        return campaign;
    }

    @Override
    public Campaign findById(String campaignId) {
        return store.get(campaignId);
    }
}
