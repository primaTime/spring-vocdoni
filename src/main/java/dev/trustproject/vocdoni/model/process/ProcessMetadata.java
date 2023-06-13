package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessMetadata {

    private String version;
    private HashMap<String, String> title;
    private HashMap<String, String> description;
    private ProcessMedia media;
    private HashMap<String, Object> meta;
    private ArrayList<VocdoniQuestion> questions;
    private Results results;
}
