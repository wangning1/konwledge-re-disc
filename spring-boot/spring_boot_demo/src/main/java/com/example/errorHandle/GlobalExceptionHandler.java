package com.example.errorHandle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangning on 2017/10/26.
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public void defaultErrorHandle(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        logger.info("Global Error handler.");
//        e.printStackTrace();
        Map<String,Object> result = new HashMap<>();
        result.put("error", e.getMessage());
        logger.info("result", result);
        modelAndView.setViewName("error");
//        return modelAndView;
    }
}
