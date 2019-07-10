package com.web.controller;

import com.web.Exception.ControllerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangning on 2017/1/17.
 */
@Controller
@RequestMapping(value = "/error")
public class TestGlobalExceptionController extends BaseController{

  @RequestMapping("/anno")
  public String testException(Integer errorId) throws Exception {
    switch (errorId){
      case 1:
        throw new ControllerException("Controller");
      default:
        throw new Exception("error");
    }
  }

}
