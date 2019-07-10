package com.test;

import com.memcache.api.IMemcacheService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wangning on 2017/7/7.
 */
@Controller
@RequestMapping("dubbo_memcache")
public class DubboMemcacheTest {

    @Autowired
    private IMemcacheService cache;

    private static Log logger = LogFactory.getLog(DubboMemcacheTest.class);



    @RequestMapping("/addToCache")
    @ResponseBody
    public String addToMemCache(){
        logger.info(" " + cache);
        if(cache == null){
            return "get cache error.";
        }
        boolean result = cache.add("memcacheKey", "Hello dubbo memcache", 0);
        logger.info(" " + result);
        if(result){
            return "add to memcache success.";
        }else {
            return "add to memcache faild.";
        }
    }


    @RequestMapping("getFromCache")
    @ResponseBody
    public String getFromCache(String key){
        if(cache == null){
            return "get key error.";
        }
       String value = (String) cache.get(key);
        if(value == null){
            return "value not exit.";
        }else {
            return value;
        }
    }

}
