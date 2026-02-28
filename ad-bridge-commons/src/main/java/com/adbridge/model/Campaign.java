package com.adbridge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String objective;

    @JsonProperty
    private int dailyBudget;

    @JsonProperty
    @Builder.Default
    private CampaignStatus status = CampaignStatus.ACTIVE;

    @JsonProperty
    @Builder.Default
    private List<Platform> platforms = new ArrayList<>();

    @JsonProperty
    @Builder.Default
    private List<String> locations = new ArrayList<>();

    @JsonProperty
    private Integer ageMin;

    @JsonProperty
    private Integer ageMax;

    @JsonProperty
    @Builder.Default
    private List<String> interests = new ArrayList<>();

    @JsonProperty
    private String headline;

    @JsonProperty
    private String body;

    @JsonProperty
    private String cta;

    @JsonProperty
    private String landingUrl;

    @JsonProperty
    @Builder.Default
    private List<PlatformCampaign> platformCampaigns = new ArrayList<>();

    @JsonProperty
    @Builder.Default
    private List<InsightsSnapshot> insights = new ArrayList<>();

    @JsonProperty
    @Builder.Default
    private Instant createdAt = Instant.now();

    @JsonProperty
    @Builder.Default
    private Instant updatedAt = Instant.now();
}
