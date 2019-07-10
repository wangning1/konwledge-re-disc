package com.winner.reflect_proxy.ReflectOperate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * Created by wangning on 2017/5/10.
 * 获取类的完整结构
 * 1.获取父类
 * 2.获取实现的接口
 * 3.获取本类的所有属性，取得实现接口或父类的属性
 * 4.获取所有方法
 */
public class ClassStructure {

    public static void main(String[] args) throws Exception {
        Class sonClazz = Class.forName("com.winner.reflect_proxy.BaseClass.Son");
        Class fatherClazz = Class.forName("com.winner.reflect_proxy.BaseClass.Father");

        Class parentClazz = sonClazz.getSuperclass();
        System.out.println(parentClazz.getName());

        //获取所有实现的接口
        System.out.println("----获取所有实现的接口-----");
        System.out.println("-----------父类-----------");
        Class[] fatherInterfaces = fatherClazz.getInterfaces();
        for (int i = 0; i < fatherInterfaces.length; i++) {
            System.out.println("接口" + (i + 1) + fatherInterfaces[i].getName());
        }
        System.out.println("-----------子类-----------");
        Class[] sonInterfaces = sonClazz.getInterfaces();
        for (int i = 0; i < sonInterfaces.length; i++) {
            System.out.println("接口" + (i + 1) + sonInterfaces[i].getName());
        }
        System.out.println("----获取所有属性-----");
        System.out.println("-----------父类-----------");
        Field[] fatherFields = fatherClazz.getDeclaredFields();
        for (Field field : fatherFields) {
            System.out.println(field.getName());
        }

        System.out.println("-----------子类-----------");
        //本类所声明的
        System.out.println("===getDeclaredFields");
        Field[] sonFileds = sonClazz.getDeclaredFields();
        for (Field field : sonFileds) {
            int mx = field.getModifiers();
            Class type = field.getType();
            System.out.println("修饰符：" + Modifier.toString(mx) + " " + type.getName());
        }

        System.out.println("===");
        Field[] allFileds = sonClazz.getFields();
        for (Field field : allFileds) {
            int mx = field.getModifiers();
            System.out.println("修饰符：" + Modifier.toString(mx) + " " + field.getName());
        }
        System.out.println("----获取所有方法-----");
        System.out.println("-----------父类-----------");
        Method[] fatherMethods = fatherClazz.getMethods();
        for (Method method : fatherMethods) {
            Class returnType = method.getReturnType();
            Class[] params = method.getParameterTypes();
            int mod = method.getModifiers();
            Class[] excs = method.getExceptionTypes();

            System.out.print(Modifier.toString(mod) + " " + returnType.getName() + " " + method.getName() + " (");

            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getName());
                if (i < params.length - 1) {
                    System.out.print(",");
                }
            }

            System.out.print(")");
            if (excs.length > 0){
                System.out.print("throws");
            }
            for (int i = 0; i < excs.length; i++) {
                System.out.print(excs[i].getName());
                if (i < excs.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();

        }

        System.out.println("-----------子类-----------");
        Method[] sonMethods = fatherClazz.getDeclaredMethods();
        for (Method method : sonMethods) {
            Class returnType = method.getReturnType();
            Class[] params = method.getParameterTypes();
            int mod = method.getModifiers();
            Class[] excs = method.getExceptionTypes();

            System.out.print(Modifier.toString(mod) + " " + returnType.getName() + " " + method.getName()+"(");
            for (int i = 0; i < params.length; i++) {
                System.out.print(params[i].getName());
                if (i < params.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.print(")");
            if (excs.length > 0){
                System.out.print("throws");
            }
            for (int i = 0; i < excs.length; i++) {
                System.out.print(excs[i].getName());
                if (i < excs.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();

        }

    }


}
