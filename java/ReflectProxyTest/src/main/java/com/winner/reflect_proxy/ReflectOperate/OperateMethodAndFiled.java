package com.winner.reflect_proxy.ReflectOperate;

import com.winner.reflect_proxy.BaseClass.Son;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wangning on 2017/5/10.
 *
 * 通过反射操作类的方法
 *
 * 通过反射操作类的属性
 *
 */
public class OperateMethodAndFiled {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.winner.reflect_proxy.BaseClass.Son");

        //操作类的方法
        Method method = clazz.getDeclaredMethod("canSee");
        method.invoke(clazz.newInstance());


        //操作类的属性
        Field field = clazz.getDeclaredField("mouse");
        field.setAccessible(true);
        Son son = (Son) clazz.newInstance();
        field.set(son,"small mouse");
        System.out.println(son.getMouse());


    }

}
