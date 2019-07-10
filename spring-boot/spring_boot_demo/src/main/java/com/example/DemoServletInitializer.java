package com.example;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by wangning on 2017/10/30.
 */
public class DemoServletInitializer extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

//        return super.configure(builder);
        return builder.sources(DemoApplication.class);
    }
}
