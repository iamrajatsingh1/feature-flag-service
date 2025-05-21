package com.example.featureflags.repository;

import com.example.featureflags.model.FeatureFlag;
import com.example.featureflags.model.ClientFeatureFlag;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FeatureFlagRepository {
    private final Map<String, FeatureFlag> featureFlags = new HashMap<>();
    private final Map<String, List<ClientFeatureFlag>> clientFeatureFlags = new HashMap<>();

    public FeatureFlag saveFeatureFlag(FeatureFlag featureFlag) {
        featureFlags.put(featureFlag.getId(), featureFlag);
        return featureFlag;
    }

    public Optional<FeatureFlag> findFeatureFlagById(String id) {
        return Optional.ofNullable(featureFlags.get(id));
    }

    public List<FeatureFlag> getAllFeatureFlags() {
        return new ArrayList<>(featureFlags.values());
    }

    public void saveClientFeatureFlag(String clientId, ClientFeatureFlag clientFeatureFlag) {
        clientFeatureFlags.computeIfAbsent(clientId, k -> new ArrayList<>()).add(clientFeatureFlag);
    }

    public List<ClientFeatureFlag> getClientFeatureFlags(String clientId) {
        return clientFeatureFlags.getOrDefault(clientId, Collections.emptyList());
    }
}
