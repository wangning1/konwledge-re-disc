package com.wn.juc.TestThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangning on 2017/3/17.
 * 一、线程池：提供一个线程队列，队列中保存所有等待状态的线程，避免创建和销毁额外的开销，提高响应速率
 * <p>
 * 二、线程池的体系结构
 * java.util.concurrent.Executor:复制线程使用和调度的根接口
 *      |--*ExecutorService 子接口：线程池的主要接口
 *         |--ThreadPoolExecutor 线程池的实现类
 *         |--scheduledExecutorService 子接口：负责线程的调度
 *              |--ScheduledThreadPoolExecutor: 继承ThreadPoolExecutor ，实现scheduledExecutorService
 * 三、工具类 Executors
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量
 * ExecutorService newSingleThreadPool():创建单个线程的线程次，线程池中只有一个线程
 * <p>
 * ScheduledExecutorService newScheduleThreadPool() :创建固定的大小的线程池，可以延迟或定时的执行任务
 */
public class TestThreadPool {

    public static void main(String[] args){
        for(int i=0;i<2;i++){
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for(int j=0;j<3;j++){
              ThreadDemo threadDemo = new ThreadDemo();
                executorService.submit(threadDemo);
            }
            executorService.shutdown();
        }


    }

}

class ThreadDemo implements Runnable {

    public void run() {
        int sum = 0;
        for (int i = 0; i < 100; i++){
            sum += i;
        }
        System.out.println(Thread.currentThread().getName()+" "+sum);
    }
}
