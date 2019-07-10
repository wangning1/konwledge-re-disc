package com.winner.DynamicProxy.JDKProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by wangning on 2017/7/10.
 */
public class ProxyMain {

    public static void main(String[] args){
        ElectricCar car = new ElectricCar();
        //1、获取与目标类相对应的loader
        ClassLoader loader = car.getClass().getClassLoader();
        //2、获取ElectricCar 锁实现的接口
        Class[] interfaces = car.getClass().getInterfaces();
        //3、设置一个来自代理传过来的方法调用请求处理器，处理所有代理对象上的方法调用
        InvocationHandler invocationHandler = new InvocationHandlerImpl(car);
        /*
          4.根据上面提供的信息，创建代理对象 在这个过程中，
            a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
            b.然后根据相应的字节码转换成对应的class，
            c.然后调用newInstance()创建实例
         */
       Object obj = Proxy.newProxyInstance(loader,interfaces,invocationHandler);
        Vehicle vehicle = (Vehicle) obj;
        vehicle.dirve();
        Rechargable rechargable = (Rechargable) obj;
        rechargable.reCharge(100L);
        ProxyUtils.generateClassFile(car.getClass() , "EletricCar");
    }

}
