package com.aop.AopController;

import com.annotation.CacheEvict;
import com.annotation.Cacheable;
import com.aop.AopAspect.CacheAnnotationAspect;
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
@RequestMapping("cacheHandl")
public class CacheAnnotationAspectController {

    private final static Log logger = LogFactory.getLog(CacheAnnotationAspect.class);

    @RequestMapping("/getFromCache")
    @ResponseBody
    @Cacheable(value = "result_List",fieldKey = "#id",expireTime = 300)
    public List<Integer> getFromCache(Long id){
        List<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i<10 ; i++){
            result.add(i);
        }
        logger.info("---此數據不是從緩存中獲取---");
        return result;
    }


    @RequestMapping("/removeFromCache")
    @ResponseBody
    @CacheEvict(value = "result_List",fieldKey = "#id")
    public String removeFromCache(Long id){
        return "清除緩存";
    }
}
