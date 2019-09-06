package com.winner.eureka.feginconsumer.service;

import org.springframework.stereotype.Component;

/**
 * @Author: ningwang216580
 */
@Component
public class SayHiServiceHystric implements SayHiService {

    @Override
    public String feginSayHi() {
        return "sorry feign invoke occur error";
    }
}
