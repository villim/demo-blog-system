package io.github.villim.blog.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.FileSystemXmlConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

@Configuration("hazelcast-cache-context")
@EnableCaching
public class HazelcastCacheConfig {

    private static final Logger LOG = LoggerFactory.getLogger(HazelcastCacheConfig.class);

    @Bean
    public Config config() {
        Config config = null;

        try {
            config = new FileSystemXmlConfig("/opt/demo/config/blog/hazelcast.cache.xml");
        } catch (FileNotFoundException e) {
            LOG.error("Cant load hazelcast.cache.xml", e);
        }

        return config;
    }

    @Bean
    @Autowired
    HazelcastInstance cacheHazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }

}
