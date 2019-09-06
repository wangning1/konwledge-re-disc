package com.winner.eureka.serviceribbonconsumer.controller;

import com.winner.eureka.serviceribbonconsumer.service.SayHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ningwang216580
 */
@RestController
public class SayHiController {

    @Autowired
    SayHiService sayHiService;

    @RequestMapping("/sayHi")
    public String sayHi() {
        return sayHiService.sayHi("ribbon");
    }

}
