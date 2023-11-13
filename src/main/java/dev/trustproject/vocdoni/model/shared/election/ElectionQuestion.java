package dev.trustproject.vocdoni.model.shared.election;

import java.util.List;
import java.util.Map;

public record ElectionQuestion(
        Map<String, String> title, Map<String, String> description, List<ElectionQuestionOption> choices) {}
