package com.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by wangning on 2017/3/10.
 * 基于注解的方式实现面向切面编程
 *
 */
@Component
@Aspect
public class TestAspactjAOP {

    private static final Logger logger = LoggerFactory.getLogger(TestAspactjAOP.class);


    @Pointcut("execution(* com.web.controller.TestController.test(..))")
    public void testAop(){}

    @Before("testAop()")
    public void aspectjBefore(){
        logger.info("---test for aspectJ's before style ---");
    }


    @After("testAop()")
    public void aspectjAfter(){
        logger.info("---test for aspectJ's after style --");
    }

    @Around("testAop()")
    public Object aspectJAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("---环绕通知的在方法执行前部分处理---");
        //获取连接点的参数
        Object[] args = joinPoint.getArgs();
        if(args != null){
            for (Object obj : args){
                System.out.println(obj);
            }
        }
        //将参数进行改变，传入到连接点中
        args[0] = 200L;
        Object object = joinPoint.proceed(args);
        logger.info("---环绕通知的方法执行后半部分处理---" + object);
        object = object + "this is execute after around";
        return object;
    }



}
