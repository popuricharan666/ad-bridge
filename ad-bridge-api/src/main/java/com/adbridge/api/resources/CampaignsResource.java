package com.adbridge.api.resources;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Inject;
import com.adbridge.model.Campaign;
import com.adbridge.model.OptimizeRequest;
import com.adbridge.model.OptimizeResponse;
import com.adbridge.api.service.CampaignService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CampaignsResource {

    private final CampaignService campaignService;

    @Inject
    public CampaignsResource(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GET
    @Path("health")
    public String healthString() {
        return "ok";
    }

    public HealthCheck.Result health() {
        return HealthCheck.Result.healthy();
    }

    @POST
    @Path("campaigns")
    public Campaign createCampaign(Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @GET
    @Path("campaigns/{campaignId}")
    public Campaign getCampaign(@PathParam("campaignId") String campaignId) {
        Campaign campaign = campaignService.getCampaign(campaignId);
        if (campaign == null) {
            throw new NotFoundException("Campaign not found");
        }
        return campaign;
    }

    @POST
    @Path("campaigns/{campaignId}/insights/sync")
    public Campaign syncInsights(@PathParam("campaignId") String campaignId) {
        Campaign campaign = campaignService.syncInsights(campaignId);
        if (campaign == null) {
            throw new NotFoundException("Campaign not found");
        }
        return campaign;
    }

    @POST
    @Path("campaigns/{campaignId}/optimize")
    public OptimizeResponse optimizeCampaign(
            @PathParam("campaignId") String campaignId,
            OptimizeRequest request
    ) {
        boolean apply = request != null && request.isApply();
        OptimizeResponse response = campaignService.optimizeCampaign(campaignId, apply);
        if (response.getCampaign() == null) {
            throw new NotFoundException("Campaign not found");
        }
        return response;
    }
}
