package com.example.CusCommondLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by wangning on 2017/10/23.
 */
@Component
@Order(value = 2)
public class MyStartupRunner2 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>服务启动，执行加载数据操作222<<<<<<");
    }
}
