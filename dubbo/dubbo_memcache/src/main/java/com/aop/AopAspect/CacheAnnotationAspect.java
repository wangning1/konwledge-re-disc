package com.aop.AopAspect;

import com.annotation.CacheEvict;
import com.annotation.Cacheable;
import com.memcache.api.IMemcacheService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * Created by wangning on 2017/7/12.
 */
@Component
@Aspect
public class CacheAnnotationAspect extends BaseSpelAspect {

    private final static Log logger = LogFactory.getLog(CacheAnnotationAspect.class);

    @Autowired
    private IMemcacheService cacheClient;

    @Around("@annotation(cache)")
    public Object cacheObject(ProceedingJoinPoint joinPoint, Cacheable cache) throws Throwable {

        if(cacheClient == null){
            logger.info(" --cache對象不存在-- " );
            return joinPoint.proceed();
        }
        StandardEvaluationContext spelContext = initSpelContext(joinPoint);
        Boolean condition = parseSpelExpression(spelContext, cache.condition(), Boolean.class);
        if(condition != null && !condition){
            return joinPoint.proceed();
        }

        String preKey = cache.value();
        String fieldKey = parseSpelExpression(spelContext,cache.fieldKey(),String.class);
        String key = preKey + (fieldKey == null ? "" : "" + fieldKey);
        logger.info(" ---prekey--- " + preKey);
        logger.info(" ---fieldKey--- " + fieldKey);
        logger.info(" ---key--- " + key);
        try {
            Object result = cacheClient.get(key);
            if(result != null){
                logger.info(" ---從緩存中獲取結果--- " + result);
                return result;
            }
        }catch (Exception e){
            logger.info("獲取失敗");
        }

        Object obj = joinPoint.proceed();
        //放入緩存
        if(obj != null){
            logger.info(" ---將結果放入到緩存中--- ");
            int expireTime = cache.expireTime();
            if(expireTime > 0){
                cacheClient.add(key,obj,expireTime);
            }else {
                cacheClient.add(key,obj,0);
            }
        }
        return obj;
    }


    @Around("@annotation(cacheEvict)")
    public Object cacheRemove(ProceedingJoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
        Object ret = joinPoint.proceed();
        if(cacheClient == null){
            return ret;
        }

        StandardEvaluationContext spelContext =  initSpelContext(joinPoint);
        Boolean condition = parseSpelExpression(spelContext, cacheEvict.condition(), Boolean.class);
        if(condition != null && !condition){
            return ret;
        }

        String preKey = cacheEvict.value();
        String fieldKey = parseSpelExpression(spelContext,cacheEvict.fieldKey(),ret,String.class);
        String key = preKey + (fieldKey == null ? "" : "" + fieldKey);
        try {
            cacheClient.delete(key);
        }catch (Exception e){
            logger.info("清除緩存失敗 key :" + key);
        }
        return ret;
    }

}
