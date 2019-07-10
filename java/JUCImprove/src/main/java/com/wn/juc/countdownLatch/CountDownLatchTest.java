package com.wn.juc.countdownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wangning on 2017/3/16.
 * 业务说明： 统计多个线程执行完成使用的时间、 或者一些商城统计各类商品金额平均，最后计算总平均
 *
 * 现象：通过闭锁CountDownLatch 可以让多个使多个线程执行完成后才继续执行后续操作
 *
 * 注意：1、设置等待的线程不能超过线程的总数，否则不能结束程序的执行
 *      2、确保在一个线程执行完成后执行countDown操作
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(20);
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            new Thread(ld).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
        }
        long end = System.currentTimeMillis();
        System.out.println("使用时间：" + (end - start));

    }

}

class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
       try {
           for (int i = 0; i < 100; i++) {
               if (i % 2 != 0) {
                   System.out.println(i);
               }
           }
       }finally {
           latch.countDown();
       }
    }
}