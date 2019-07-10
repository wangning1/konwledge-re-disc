package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by wangning on 2017/10/24.
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.example..Controller.*.*(..))")
    public void logPointCut(){

    }

    @Before("logPointCut()")
    public void beforeInvokeLog(JoinPoint point){
        //接收到请求，记录请求的内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("请求地址：" + request.getRequestURI());
        logger.info("class method ：" + point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        logger.info("参数：" + Arrays.toString(point.getArgs()));
    }


    @AfterReturning(returning = "res",pointcut = "logPointCut()")
    public void afterInvokeLog(Object res){
        logger.info("返回值：" + res);
    }

    @Around("logPointCut()")
    public Object invokeTimeSpend(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        logger.info("执行请求消耗时间：" + (System.currentTimeMillis() - startTime) + "ms");
        return result;
    }

}
