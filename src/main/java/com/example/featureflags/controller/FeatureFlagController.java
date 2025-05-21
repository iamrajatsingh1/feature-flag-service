package com.example.featureflags.controller;

import com.example.featureflags.model.FeatureFlag;
import com.example.featureflags.service.FeatureFlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feature-flags")
public class FeatureFlagController {

    @Autowired
    private FeatureFlagService service;

    @PostMapping
    public ResponseEntity<FeatureFlag> createFeatureFlag(@RequestBody FeatureFlag featureFlag) {
        return ResponseEntity.ok(service.createFeatureFlag(featureFlag));
    }

    @PostMapping("/clients/{clientId}/enable/{flagId}")
    public ResponseEntity<String> enableFeatureFlagForClient(@PathVariable String clientId, @PathVariable String flagId) {
        service.enableFeatureFlagForClient(clientId, flagId);
        return ResponseEntity.ok("Feature flag enabled successfully.");
    }

    @GetMapping
    public ResponseEntity<List<FeatureFlag>> getAllFeatureFlags() {
        return ResponseEntity.ok(service.getAllFeatureFlags());
    }
}
