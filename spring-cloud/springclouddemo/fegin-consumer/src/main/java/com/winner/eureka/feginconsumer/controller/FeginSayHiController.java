package com.winner.eureka.feginconsumer.controller;

import com.winner.eureka.feginconsumer.service.SayHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ningwang216580
 */
@RestController
public class FeginSayHiController {

    @Autowired
    SayHiService sayHiService;

    @RequestMapping("/feignSayHi")
    public String feignSayHi() {
        return sayHiService.feginSayHi();
    }
}
