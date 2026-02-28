package com.adbridge.api.config;

import com.adbridge.api.client.AdPlatformClient;
import com.adbridge.api.client.AdPlatformClientRegistry;
import com.adbridge.api.client.GoogleAdsClient;
import com.adbridge.api.client.MetaAdsClient;
import com.adbridge.api.client.XAdsClient;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.adbridge.dal.dao.CampaignDao;
import com.adbridge.dal.dao.InMemoryCampaignDao;
import com.adbridge.api.service.CampaignService;
import com.adbridge.api.service.CampaignServiceImpl;
import com.adbridge.model.Platform;
import java.util.EnumMap;
import java.util.Map;

public class AdBridgeApiModule extends AbstractModule {
    private final AdBridgeApiConfiguration configuration;

    public AdBridgeApiModule(AdBridgeApiConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(CampaignDao.class).to(InMemoryCampaignDao.class).in(Singleton.class);
        bind(CampaignService.class).to(CampaignServiceImpl.class).in(Singleton.class);
        bind(AdPlatformClientRegistry.class).toInstance(buildClientRegistry());
    }

    private AdPlatformClientRegistry buildClientRegistry() {
        AdPlatformClientsConfiguration clientsConfiguration = configuration.getAdPlatformClients();

        MetaAdsClient metaClient = new MetaAdsClient(clientsConfiguration.getMeta());
        GoogleAdsClient googleClient = new GoogleAdsClient(clientsConfiguration.getGoogle());
        XAdsClient xClient = new XAdsClient(clientsConfiguration.getX());

        Map<Platform, AdPlatformClient> clients = new EnumMap<>(Platform.class);
        clients.put(Platform.META, metaClient);
        clients.put(Platform.GOOGLE, googleClient);
        clients.put(Platform.X, xClient);
        return new AdPlatformClientRegistry(clients);
    }
}
