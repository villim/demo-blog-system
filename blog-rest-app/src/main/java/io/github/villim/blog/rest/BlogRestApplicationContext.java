package io.github.villim.blog.rest;

import com.google.gson.Gson;
import io.github.villim.blog.infrastructure.PersistenceJPAContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration("blog-rest-context")
@Import({PersistenceJPAContext.class, SwaggerConfig.class, HazelcastConfig.class})
@EnableJpaRepositories(basePackages = {"io.github.villim.blog.infrastructure.springdata"})
@ComponentScan(basePackages = {"io.github.villim.blog.domain", "io.github.villim.blog.infrastructure", "io.github.villim.blog.rest"})
@PropertySource({"file:/opt/demo/config/blog/blog-public.properties", "classpath:version.properties"})
public class BlogRestApplicationContext {

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
