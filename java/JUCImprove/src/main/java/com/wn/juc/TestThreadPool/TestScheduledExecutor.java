package com.wn.juc.TestThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangning on 2017/3/17.
 */
public class TestScheduledExecutor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);


        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        for (int i = 0; i < 5; i++) {
           Future<Integer> future = pool.schedule(new ThreadDemoCallable(), 3, TimeUnit.SECONDS);
            futureList.add(future);
        }

        for (Future future : futureList){
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}

class ThreadDemoCallable implements Callable<Integer> {

    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName());
        return sum;
    }
}
