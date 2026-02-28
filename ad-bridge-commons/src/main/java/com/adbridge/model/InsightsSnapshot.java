package com.adbridge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsightsSnapshot {
    @JsonProperty
    private Platform platform;

    @JsonProperty
    private int impressions;

    @JsonProperty
    private int clicks;

    @JsonProperty
    private double spend;

    @JsonProperty
    private double ctr;

    @JsonProperty
    private double cpc;

    @JsonProperty
    @Builder.Default
    private Instant capturedAt = Instant.now();
}
