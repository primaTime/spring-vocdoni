package dev.trustproject.vocdoni.model.census;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CensusProof {

    private String weight;
    private String censusProof;
    private String value;
}
