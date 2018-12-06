package io.github.villim.blog.cache;

import io.villim.blog.infrastructure.PersistenceJPAContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration("blog-cache-context")
@Import({PersistenceJPAContext.class, HazelcastCacheConfig.class})
@EnableJpaRepositories(basePackages = {"io.github.villim.blog.infrastructure.springdata"})
@ComponentScan(basePackages = {"io.github.villim.blog.domain", "io.github.villim.blog.infrastructure", "io.github.villim.blog.cache"})
@PropertySource({"classpath:version.properties"})
@EnableAsync
public class CacheRestApplicationContext {

}
