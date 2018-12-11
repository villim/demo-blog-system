package io.villim.blog.docker.controller;

import io.villim.blog.domain.Version;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DockerRestController {


    @RequestMapping(method = RequestMethod.GET, value = "/version", produces = "application/json")
    public Version version() {
        return new Version("Docker Application", "1.0.0-SNAPSHOT");
    }

}
