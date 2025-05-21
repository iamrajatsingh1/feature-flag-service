package com.example.featureflags.service;

import com.example.featureflags.model.FeatureFlag;
import com.example.featureflags.model.ClientFeatureFlag;
import com.example.featureflags.repository.FeatureFlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureFlagService {

    @Autowired
    private FeatureFlagRepository repository;

    public FeatureFlag createFeatureFlag(FeatureFlag featureFlag) {
        if (featureFlag.getParentFlagId() != null) {
            Optional<FeatureFlag> parentFlag = repository.findFeatureFlagById(featureFlag.getParentFlagId());
            if (parentFlag.isEmpty()) {
                throw new IllegalArgumentException("Parent feature flag does not exist.");
            }
        }
        return repository.saveFeatureFlag(featureFlag);
    }

    public void enableFeatureFlagForClient(String clientId, String flagId) {
        FeatureFlag flag = repository.findFeatureFlagById(flagId)
                .orElseThrow(() -> new IllegalArgumentException("Feature flag not found"));

        if (flag.getParentFlagId() != null) {
            FeatureFlag parentFlag = repository.findFeatureFlagById(flag.getParentFlagId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent feature flag not found"));
            if (!parentFlag.isEnabled()) {
                throw new IllegalArgumentException("Parent feature flag is not enabled.");
            }
        }
        repository.saveClientFeatureFlag(clientId, new ClientFeatureFlag(clientId, flagId, true));
    }

    public List<FeatureFlag> getAllFeatureFlags() {
        return repository.getAllFeatureFlags();
    }
}
