package io.villim.blog.docker;

import io.villim.blog.domain.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;

@Import({DockerApplicationContext.class})
@SpringBootApplication
public class DockerApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(DockerApplication.class);
        String pid = System.getenv(Constants.SPRING_PID_FILE_PROPERTY_NAME);
        if (StringUtils.isNotBlank(pid)) {
            app.addListeners(new ApplicationPidFileWriter(pid));
        }
        app.run();
    }

}
