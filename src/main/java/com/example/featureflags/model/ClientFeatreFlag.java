package com.example.featureflags.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientFeatureFlag {
    private String clientId;
    private String featureFlagId;
    private boolean isEnabled;
}
