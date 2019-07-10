package com.example.aspect;

import com.example.annotation.TestAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by wangning on 2017/10/24.
 */
@Aspect
@Order(-99)
@Component
public class TestAspect {

    private final static Logger logger = LoggerFactory.getLogger(TestAspect.class);


    @Around("@annotation(testAnnotation)")
    public Object aroundTest(ProceedingJoinPoint point, TestAnnotation testAnnotation) throws Throwable {
        logger.info("---around aspect : before method run...");
        logger.info("---annotation value :" + testAnnotation.name());
        Object result = point.proceed();
        logger.info("---around aspect : after method run...");
        return result;
    }

    @Before("@annotation(testAnnotation)")
    public void beforeTest(TestAnnotation testAnnotation){
        logger.info(">>>before aspect--" + testAnnotation.name());
    }

}
