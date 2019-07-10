package com.winner.reflect_proxy.ReflectOperate;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangning on 2017/5/10.
 *
 * 通过反射操作集合
 *
 * 通过反射操作数组
 *   --获取数组的信息
 *   --修改数组的大小
 */
public class OperateCollectionAndArray {

    public static void main(String[] args)throws Exception{
        //操作集合，在Integer类型的集合中存放String元素
        List<Integer> integerList = new ArrayList<Integer>();
        Method method = integerList.getClass().getDeclaredMethod("add", Object.class);
        method.invoke(integerList, "hello");
        integerList.add(1);
        System.out.println(integerList.get(0));
        System.out.println(integerList.get(1).getClass().getName());


        //操作数组
        int[] tempArr = {1, 2, 3, 4, 5};
        Class clazz = tempArr.getClass();
        System.out.println("数组类型 " + clazz.getName());
        System.out.println("数组长度 " + Array.getLength(tempArr));
        System.out.println("数组第一个元素 " + Array.get(tempArr, 0));
        Array.set(tempArr, 0, 100);
        System.out.println("修改后数组第一个元素 " + Array.get(tempArr, 0));

       //进行数组扩容
        Class type = tempArr.getClass().getComponentType();
        int originLen = tempArr.length;
        Object newArr = Array.newInstance(type, originLen *2 );
        System.out.println("新数组的大小" + Array.getLength(newArr));
        System.arraycopy(tempArr, 0, newArr, 0, tempArr.length);
        System.out.println("新数组的第一个元素" + Array.get(newArr, 0));

    }


}
