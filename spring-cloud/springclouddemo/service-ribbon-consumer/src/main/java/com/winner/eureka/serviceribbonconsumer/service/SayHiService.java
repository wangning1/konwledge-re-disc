package com.winner.eureka.serviceribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: ningwang216580
 */
@Service
public class SayHiService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "sayHiError")
    public String sayHi(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/sayHi", String.class) + name;
    }

    private String sayHiError(String name) {
        return "say hi port :" + name + " occur error";
    }
}
