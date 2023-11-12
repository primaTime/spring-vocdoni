package dev.trustproject.vocdoni.model.account;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountMetadata {

    private String version;
    private List<String> languages;
    private Map<String, Object> name;
    private Map<String, Object> description;
    private Map<String, Object> newsFeed;
    private AccountMedia media;
    private Map<String, Object> meta;
}
