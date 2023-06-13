package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

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
