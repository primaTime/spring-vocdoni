package dev.trustproject.vocdoni.autoconfigure;

import dev.trustproject.vocdoni.VocdoniClient;
import dev.trustproject.vocdoni.configuration.VocdoniProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(VocdoniClient.class)
@EnableConfigurationProperties(VocdoniProperties.class)
@AllArgsConstructor
public class VocdoniAutoConfiguration {

    private final VocdoniProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public VocdoniClient vocdoniClient(VocdoniClient.TransactionSigner transactionSigner) {
        return new VocdoniClient(properties, transactionSigner);
    }

}
