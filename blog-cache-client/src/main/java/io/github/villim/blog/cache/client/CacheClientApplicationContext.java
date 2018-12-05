package io.github.villim.blog.cache.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan
public class CacheClientApplicationContext {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
