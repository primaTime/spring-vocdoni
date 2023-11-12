package dev.trustproject.vocdoni.model.process;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocdoniQuestion {

    private Map<String, String> title;
    private Map<String, String> description;
    private List<VocdoniOption> choices;

    public VocdoniQuestion(String title, String description, List<VocdoniOption> choices) {
        this.title = Map.of("default", title);
        this.choices = choices;

        if (description != null) {
            this.description = Map.of("default", description);
        }
    }
}
