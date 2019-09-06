package com.winner.eureka.serviceproducer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ningwang216580
 */
@Slf4j
@RestController
public class HiController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/sayHi")
    public String sayHi() {
        return "Hello eureka , port : " + port;
    }


}
