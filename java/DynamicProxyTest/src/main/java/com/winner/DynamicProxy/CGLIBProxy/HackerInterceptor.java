package com.winner.DynamicProxy.CGLIBProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by wangning on 2017/7/10.
 */
public class HackerInterceptor implements MethodInterceptor{

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("..before invoke method [name：" +method.getName());
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("..after invoke method [result:" + result);
        return result;
    }

}
