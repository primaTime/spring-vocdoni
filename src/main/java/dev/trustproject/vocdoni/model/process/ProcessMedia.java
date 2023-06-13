package dev.trustproject.vocdoni.model.process;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessMedia {

    private String header;
    private String streamUri;
}
