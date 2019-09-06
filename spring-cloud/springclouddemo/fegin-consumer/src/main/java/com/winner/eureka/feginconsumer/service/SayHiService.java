package com.winner.eureka.feginconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: ningwang216580
 */
@FeignClient(value = "service-hi", fallback = SayHiServiceHystric.class)
public interface SayHiService {

    @RequestMapping(value = "/sayHi")
    String feginSayHi();
}
