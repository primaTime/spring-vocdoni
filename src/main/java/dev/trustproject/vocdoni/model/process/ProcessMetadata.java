package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessMetadata {

    private String version;
    private Map<String, String> title;
    private Map<String, String> description;
    private ProcessMedia media;
    private Map<String, Object> meta;
    private List<VocdoniQuestion> questions;
    private Results results;
}
