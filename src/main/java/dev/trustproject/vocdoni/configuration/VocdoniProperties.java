package dev.trustproject.vocdoni.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "vocdoni")
public class VocdoniProperties {

    private HostsConfig hosts = new HostsConfig();

    public String apiHost() {
        return hosts.getApi();
    }

    public String faucetHost() {
        return hosts.getFaucet();
    }

    @Data
    public static class HostsConfig {
        private String api;
        private String faucet;
    }
}
