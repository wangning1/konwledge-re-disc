package com.winner.reflect_proxy.ReflectOperate;

import com.winner.reflect_proxy.BaseClass.Son;

/**
 * Created by wangning on 2017/5/10.
 *Class类的使用：
 * 实例化class对象的三种方式：
 * 1.Class静态方法 ： Class.class
 * 2.通过对象获取  :  xxx.getClass()
 * 3.通过包的全限定名进行获取 ： Class.forName("xx.xxx.xxx") 较常用
 */
public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;

        //获取class对象的三种方式
        class1 = Son.class;
        class2 = new Son().getClass();
        class3 = Class.forName("com.winner.reflect_proxy.BaseClass.Son");

        System.out.println("类的名称：" + class1.getName());
        System.out.println("类的名称：" + class2.getName());
        System.out.println("类的名称：" + class3.getName());

    }

}
