package com.winner.reflect_proxy.ReflectOperate;

import com.winner.reflect_proxy.BaseClass.Son;

import java.lang.reflect.Constructor;

/**
 * Created by wangning on 2017/5/10.
 * 通过反射创建实例化类的对象的两种方式：
 * 1.通过newInstance（）
 * 2.通过构造函数创建
 *
 * 获取类的所有构造函数
 */
public class NewObjectTest {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.winner.reflect_proxy.BaseClass.Son");

        //第一种方式
        Son son = (Son) clazz.newInstance();
        son.setEyes("nearsighted");

        System.out.println(son.getEyes());
        System.out.println("------------------------");

        //方式二，获取所有的构造函数，通过参数进行实例化
        Constructor[] constructors = clazz.getConstructors();
        int j = 0;
        for (Constructor constructor : constructors) {
            Class[] paramClazz = constructor.getParameterTypes();
            System.out.print("构造函数：con["+ j +"](");
            for (int i = 0; i < paramClazz.length; i++) {
               if(i == paramClazz.length -1){
                   System.out.print( paramClazz[i].getName());
               }else {
                   System.out.print( paramClazz[i].getName()+ ", ");
               }
            }
            System.out.println(")");
            j++;
        }

       son = (Son) constructors[1].newInstance("clear see","big nose");

        System.out.println(son.getEyes());

    }

}
