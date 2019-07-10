package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangning on 2017/7/7.
 */
@Controller
public class IndexController {

    private static Log logger = LogFactory.getLog(IndexController.class);

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        logger.info("--index--");
        return "Dubbo 集成 memcache";
    }
}
