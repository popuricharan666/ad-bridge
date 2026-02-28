package com.adbridge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptimizationAction {
    @JsonProperty
    private Platform platform;

    @JsonProperty
    private String action;

    @JsonProperty
    private String reason;

    @JsonProperty
    @Builder.Default
    private Map<String, Object> changes = new HashMap<>();
}
