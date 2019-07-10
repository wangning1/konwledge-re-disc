package com.winner.DynamicProxy.CGLIBProxy;

import net.sf.cglib.asm.ClassVisitor;
import net.sf.cglib.asm.ClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * Created by wangning on 2017/7/10.
 */
public class CGLIBMain {

    public static void main(String[] args){
        Programmer programmer = new Programmer();
        HackerInterceptor hackerInterceptor = new HackerInterceptor();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(programmer.getClass());
        enhancer.setCallback(hackerInterceptor);

        Programmer proxy = (Programmer) enhancer.create();
        proxy.coding();
        proxy.learning("Java");
    }
}
