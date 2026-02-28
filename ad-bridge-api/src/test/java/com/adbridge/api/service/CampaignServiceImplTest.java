package com.adbridge.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.adbridge.api.client.AdPlatformClient;
import com.adbridge.api.client.AdPlatformClientRegistry;
import com.adbridge.api.client.GoogleAdsClient;
import com.adbridge.api.client.MetaAdsClient;
import com.adbridge.api.client.XAdsClient;
import com.adbridge.api.config.AdPlatformClientsConfiguration;
import com.adbridge.dal.dao.InMemoryCampaignDao;
import com.adbridge.model.Campaign;
import com.adbridge.model.Platform;
import java.util.List;
import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CampaignServiceImplTest {

    @Test
    void createAndSyncFlow() {
        AdPlatformClientRegistry registry = buildRegistry();
        CampaignService service = new CampaignServiceImpl(new InMemoryCampaignDao(), registry);

        Campaign campaign = Campaign.builder()
                .name("Launch")
                .objective("AWARENESS")
                .dailyBudget(1000)
                .platforms(List.of(Platform.META, Platform.GOOGLE, Platform.X))
                .interests(List.of("saas"))
                .build();

        Campaign saved = service.createCampaign(campaign);
        assertNotNull(saved.getId());
        assertEquals(3, saved.getPlatformCampaigns().size());

        Campaign synced = service.syncInsights(saved.getId());
        assertEquals(3, synced.getInsights().size());
    }

    private AdPlatformClientRegistry buildRegistry() {
        AdPlatformClientsConfiguration configuration = new AdPlatformClientsConfiguration();

        Map<Platform, AdPlatformClient> clients = new EnumMap<>(Platform.class);
        clients.put(Platform.META, new MetaAdsClient(configuration.getMeta()));
        clients.put(Platform.GOOGLE, new GoogleAdsClient(configuration.getGoogle()));
        clients.put(Platform.X, new XAdsClient(configuration.getX()));
        return new AdPlatformClientRegistry(clients);
    }
}
