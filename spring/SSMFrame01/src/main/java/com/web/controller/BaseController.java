package com.web.controller;

import com.web.Exception.ControllerException;
import com.web.Exception.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 增加BaseController类，在类中使用@ExceptionHandler注解
 * Created by wangning on 2017/1/17.
 */
public class BaseController {

  @ExceptionHandler
  public String excHandler(HttpServletRequest request, Exception ex){

    request.setAttribute("ex",ex);
    //根据错误类型进行不同页面的跳转
    if(ex instanceof ControllerException){
      return "error";
    }else if(ex instanceof ServiceException){
      return "service-error";
    }else {

      return "500";
    }

  }
}
