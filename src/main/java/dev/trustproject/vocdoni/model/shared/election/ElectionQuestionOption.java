package dev.trustproject.vocdoni.model.shared.election;

import java.util.Map;

public record ElectionQuestionOption(Map<String, String> title, int value) {}
