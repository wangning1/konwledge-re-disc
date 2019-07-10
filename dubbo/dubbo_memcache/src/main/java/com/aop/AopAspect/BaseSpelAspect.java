package com.aop.AopAspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;

/**
 * Created by wangning on 2017/7/12.
 */
public class BaseSpelAspect {


    private static final Log logger = LogFactory.getLog(BaseSpelAspect.class);
    private ExpressionParser parser = new SpelExpressionParser();

    protected StandardEvaluationContext initSpelContext(JoinPoint joinPoint){
        StandardEvaluationContext spelContext = new StandardEvaluationContext();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argsName = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        if(argsName != null){
            logger.info("--- argsName ---" + Arrays.toString(argsName));
            logger.info("--- args ---" + Arrays.toString(args));
            for(int i = 0; i < argsName.length ; i++){
                spelContext.setVariable(argsName[i], args[i]);
            }
        }
        logger.info("--- target ---" + joinPoint.getTarget());
        spelContext.setRootObject(joinPoint.getTarget());
        return spelContext;
    }

    protected  <T> T parseSpelExpression(StandardEvaluationContext spelContext, String spelExpression, Class<T> clazz){
        return parseSpelExpression(spelContext, spelExpression, null,clazz);
    }

    protected <T> T parseSpelExpression(StandardEvaluationContext spelContext, String spelExpression, Object result, Class<T> clazz){
         if(spelExpression == null || spelExpression.endsWith("")){
             return null;
         }

         if(result != null){
             spelContext.setVariable("Result",result);
         }

         return parser.parseExpression(spelExpression).getValue(spelContext,clazz);

    }

}
