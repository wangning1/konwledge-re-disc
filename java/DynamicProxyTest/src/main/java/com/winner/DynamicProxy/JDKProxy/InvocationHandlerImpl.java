package com.winner.DynamicProxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangning on 2017/7/10.
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private ElectricCar car;

    public InvocationHandlerImpl(ElectricCar car){
        this.car = car;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke method " + method.getName() + " [args:" + args);
        Object result = method.invoke(car,args);
        System.out.println("after invoke method, the result is " + result );
        return result;
    }

}
