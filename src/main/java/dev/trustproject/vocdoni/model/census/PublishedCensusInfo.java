package dev.trustproject.vocdoni.model.census;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishedCensusInfo {

    private String censusID;
    private String uri;
}
