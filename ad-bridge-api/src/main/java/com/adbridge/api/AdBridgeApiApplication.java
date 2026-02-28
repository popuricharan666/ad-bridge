package com.adbridge.api;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.adbridge.api.config.AdBridgeApiConfiguration;
import com.adbridge.api.config.AdBridgeApiModule;
import com.adbridge.api.resources.CampaignsResource;
import io.dropwizard.core.Application;
import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class AdBridgeApiApplication extends Application<AdBridgeApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new AdBridgeApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "ad-bridge-api";
    }

    @Override
    public void initialize(Bootstrap<AdBridgeApiConfiguration> bootstrap) {
        // No custom bootstrap hooks required for v1.
    }

    @Override
    public void run(AdBridgeApiConfiguration configuration, Environment environment) {
        Injector injector = Guice.createInjector(new AdBridgeApiModule(configuration));

        CampaignsResource campaignsResource = injector.getInstance(CampaignsResource.class);

        environment.jersey().register(campaignsResource);
        environment.healthChecks().register("adbridge", new HealthCheck() {
            @Override
            protected Result check() {
                return campaignsResource.health();
            }
        });
    }
}
