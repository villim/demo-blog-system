package io.villim.blog.basic.controller;

import io.villim.blog.domain.Version;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BasicRestController {


    @RequestMapping(method = RequestMethod.GET, value = "/version", produces = "application/json")
    public Version version() {
        return new Version("1.0.0-SNAPSHOT", "123456789");
    }

}
