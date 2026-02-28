package com.adbridge.api.client;

import com.adbridge.model.Platform;
import java.util.EnumMap;
import java.util.Map;

public class AdPlatformClientRegistry {

    private final Map<Platform, AdPlatformClient> clients;

    public AdPlatformClientRegistry(Map<Platform, AdPlatformClient> clients) {
        this.clients = new EnumMap<>(clients);
    }

    public AdPlatformClient getClient(Platform platform) {
        return clients.get(platform);
    }
}