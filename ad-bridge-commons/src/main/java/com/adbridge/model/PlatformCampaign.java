package com.adbridge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformCampaign {
    @JsonProperty
    private Platform platform;

    @JsonProperty
    private String externalCampaignId;

    @JsonProperty
    private String externalAdsetId;

    @JsonProperty
    private String externalAdId;

    @JsonProperty
    @Builder.Default
    private CampaignStatus status = CampaignStatus.ACTIVE;

    @JsonProperty
    private String rawResponse;
}
