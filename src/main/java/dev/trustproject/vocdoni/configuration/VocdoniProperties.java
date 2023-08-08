package dev.trustproject.vocdoni.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "vocdoni")
public class VocdoniProperties {

    private ApiConfig api = new ApiConfig();
    private FaucetConfig faucet = new FaucetConfig();

    public String getApiUrl() {
        return api.getUrl();
    }

    public String getFaucetUrl() {
        return faucet.getUrl();
    }

    public String getFaucetToken() {
        return faucet.getToken();
    }

    @Data
    public static class ApiConfig {
        private String url;
    }

    @Data
    public static class FaucetConfig {
        private String url;
        private String token;
    }

}
