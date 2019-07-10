package com.wn.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by wangning on 2017/3/16.
 * 业务场景；在主线程中单开一个线程去执行，主线程接着执行，最后可以把分线程返回的结果进行在主线程中获取进行相应的处理。
 *
 * 一、创建执行线程的方式三：实现 Callable 接口。 相较于实现 Runnable 接口的方式，方法可以有返回值，并且可以抛出异常。
 * 二、执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。  FutureTask 是  Future 接口的实现类
 *
 *
 */
public class CallableTest {
    public static void main(String[] args ){
        CallableDemo cd = new CallableDemo();
        //1 执行callable方式，需要FutrueTask支持，用于接收结果
        FutureTask<Integer> result = new FutureTask<Integer>(cd);
        new Thread(result).start();

        try {
            //用于接收结算后的结果
            int sum = result.get();
            System.out.println(sum);
        } catch (Exception e) {
        }

    }
}

class CallableDemo implements Callable<Integer> {

    public Integer call() throws Exception {

        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        return sum;
    }
}

