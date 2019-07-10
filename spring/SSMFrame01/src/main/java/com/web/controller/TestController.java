package com.web.controller;

import com.web.mapper.db1.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

  @RequestMapping(value = "/test")
  @ResponseBody
  public String test(Long id){
    logger.info("--test method's args is " + id );
    System.out.println(userMapper.getUser(1L));
    return  "Hello world" + id;
  }

  @RequestMapping(value = "/freemarker")
  public String testTemplate(ModelMap modelMap){
    modelMap.addAttribute("freemarker","Hello Freemarker");
    return "freemarkerTest";
  }

  @RequestMapping(value = "jsp")
  public String testJsp()throws Exception{
    throw new Exception();
  }
}
