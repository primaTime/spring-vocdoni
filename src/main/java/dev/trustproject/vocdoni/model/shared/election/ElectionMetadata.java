package dev.trustproject.vocdoni.model.shared.election;

import java.util.List;
import java.util.Map;

public record ElectionMetadata(
        String version,
        Map<String, String> title,
        Map<String, String> description,
        ElectionMedia media,
        Map<String, Object> meta,
        List<ElectionQuestion> questions,
        ElectionResults results) {}
