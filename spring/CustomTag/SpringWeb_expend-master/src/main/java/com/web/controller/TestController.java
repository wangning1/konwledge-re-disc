package com.web.controller;

import com.web.mapper.db1.UserMapper;
import com.web.model.CustomerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangning on 2016/12/27.
 */
@Controller
@RequestMapping("/")
public class TestController {

  private final static Logger logger = LoggerFactory.getLogger(TestController.class);

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private CustomerTag customerTag;

  @RequestMapping(value = "/test")
  @ResponseBody
  public String test(Long id){
//    TestCompile testCompile = new TestCompile();
//    testCompile.setAge(18);
    logger.info("--test method's args is " + id );
//    logger.info("--testCompile age is " + testCompile.getAge());
    System.out.println(userMapper.getUser(1L));
    System.out.println(customerTag);
    return  "Hello world" + id;
  }


}
