package com.aop.AopAspect;

import com.annotation.AroundAnnotation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by wangning on 2017/7/12.
 *
 */
@Component
@Aspect
public class AroundAspect {

    private static final Log logger = LogFactory.getLog(AroundAspect.class);


    @Pointcut("execution(* com.aop.AopController.AroundAspectTestController.*(..))")
    public void pointcutMethod(){}



    @Around("pointcutMethod()")
    public Object aroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("---around--[before method]");
        Object obj = joinPoint.proceed();
        logger.info("---around--[after method]");

        return obj;
    }


    @Around("execution(* com.aop.AopController.AroundAspectTestController.*(..))")
    public Object aroundAspect2(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("---around2--[beford around method.]---");
        Object obj = joinPoint.proceed();
        logger.info("---around2--[after around method.]---");
        return obj;
    }

    @Around("@annotation(around)")
    public Object AroundAspect3(ProceedingJoinPoint joinPoint, AroundAnnotation around) throws Throwable {
        logger.info("---aroun3 with annotation -- [before around method.]");
        logger.info("---aroun3 with annotation -- [value]:" + around.value() + " [condition]:"+around.condition());
        Object object = joinPoint.proceed();
        logger.info("---around with annotion --[after around method]");
        return object;
    }


    /**
     * 注意 这里看可以带参数可不带参数，但不能使用ProceedingJoinPoit作参数
     *
     * @param joinPoint
     */

    @Before("pointcutMethod()")
    public void beforeMethod(JoinPoint joinPoint){

        logger.info("--this is before method method.");
    }


}
