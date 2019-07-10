package com.aop.AopController;

import com.annotation.AroundAnnotation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangning on 2017/7/12.
 */
@Controller
@RequestMapping("/aroundAspect")
public class AroundAspectTestController {

    private static final Log logger = LogFactory.getLog(AroundAspectTestController.class);

    @RequestMapping("/aroundMethod")
    @ResponseBody
    @AroundAnnotation(value = "test",condition = "123")
    public String aroundTest(){
        logger.info("------this is around method.");
        return "around method.";
    }

    @RequestMapping("/aroundWithCache")
    @ResponseBody
    public List<Integer> aroundWithCache(){
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < 10 ; i++){
            result.add(i);
        }
        return  result;
    }


}
