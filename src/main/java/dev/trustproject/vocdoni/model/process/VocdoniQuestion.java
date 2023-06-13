package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocdoniQuestion {

    private Map<String, String> title;
    private Map<String, String> description;
    private ArrayList<VocdoniOption> choices;

    public VocdoniQuestion(String title, String description, ArrayList<VocdoniOption> choices) {
        this.title = Map.of("default", title);
        this.description = Map.of("default", description);
        this.choices = choices;
    }
}
