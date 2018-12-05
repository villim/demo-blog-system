package io.github.villim.blog.cache;

import io.github.villim.blog.domain.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(CacheRestApplicationContext.class)
public class CacheRestApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(CacheRestApplication.class);
        String pid = System.getenv(Constants.SPRING_PID_FILE_PROPERTY_NAME);
        if (StringUtils.isNotBlank(pid)) {
            app.addListeners(new ApplicationPidFileWriter(pid));
        }
        app.run();
    }

}
