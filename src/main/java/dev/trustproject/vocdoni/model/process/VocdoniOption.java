package dev.trustproject.vocdoni.model.process;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocdoniOption {

    private Map<String, String> title;
    private int value;

    public VocdoniOption(String title, int value) {
        this.title = Map.of("default", title);
        this.value = value;
    }
}
