package com.example.Controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangning on 2017/10/23.
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "测试hello")
    public String hello() {
        return "hello Spring-boot's world!";
    }


    @RequestMapping(value = "info",method = RequestMethod.GET)
    @ApiOperation(value = "测试getInfo")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @RequestMapping(value = "listMap",method = RequestMethod.POST)
    @ApiOperation(value = "测试getLsit,限定请求为post方式",notes = "更多说明")
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("spring-boot-" + i, "" + i);
            list.add(map);
        }
        return list;
    }

}
