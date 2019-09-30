package com.winner.config.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ningwang216580
 */
@RestController
public class HelloConfigController {

    @Value("${gitconfig.hello:no value}")
    private String profile;

    @GetMapping("/info")
    public String helloConfig() {
        return profile;
    }
}
