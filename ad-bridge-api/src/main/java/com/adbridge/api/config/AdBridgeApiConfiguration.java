package com.adbridge.api.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;

public class AdBridgeApiConfiguration extends Configuration {

    @JsonProperty
    private String serviceName = "ad-bridge-api";

    @JsonProperty("adPlatformClients")
    private AdPlatformClientsConfiguration adPlatformClients = new AdPlatformClientsConfiguration();

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public AdPlatformClientsConfiguration getAdPlatformClients() {
        return adPlatformClients;
    }

    public void setAdPlatformClients(AdPlatformClientsConfiguration adPlatformClients) {
        this.adPlatformClients = adPlatformClients;
    }
}
