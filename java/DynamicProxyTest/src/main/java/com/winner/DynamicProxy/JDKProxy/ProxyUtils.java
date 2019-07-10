package com.winner.DynamicProxy.JDKProxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wangning on 2017/7/10.
 */
public class ProxyUtils {

    /**
     * 根据类的信息生成class文件保存在硬盘中
     * @param clazz
     * @param proxyName
     */
    public static void generateClassFile(Class clazz, String proxyName){
       //根据类名称和提供的代理类名称，生成字节码
        byte[] classFile =  ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String path = clazz.getResource(".").getPath();
        System.out.println(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path + proxyName + ".class");
            fos.write(classFile);
            fos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           if(fos!=null){
               try {
                   fos.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
