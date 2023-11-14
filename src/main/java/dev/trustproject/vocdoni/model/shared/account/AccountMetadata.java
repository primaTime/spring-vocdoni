package dev.trustproject.vocdoni.model.shared.account;

import java.util.List;
import java.util.Map;

public record AccountMetadata(
        String version,
        List<String> languages,
        Map<String, Object> name,
        Map<String, Object> description,
        Map<String, Object> newsFeed,
        AccountMedia media,
        Map<String, Object> meta) {}
