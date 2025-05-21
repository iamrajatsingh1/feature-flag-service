package com.example.featureflags.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureFlag {
    private String id;
    private String name;
    private String description;
    private boolean isEnabled;
    private String parentFlagId;
}
