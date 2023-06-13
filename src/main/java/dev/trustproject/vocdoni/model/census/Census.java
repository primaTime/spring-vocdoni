package dev.trustproject.vocdoni.model.census;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Census {

    private CensusOrigin censusOrigin;
    private String censusRoot;
    private String postRegisterCensusRoot;
    private String censusURL;
}
