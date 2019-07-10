package com.wn.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangning on 2017/3/17.
 * <p>
 * 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，每个线程将自己的 ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 * 如：ABCABCABC…… 依次递归
 * <p>
 * 实质，线程间的通信
 * 通过 lock的condition 可以实现更细粒度通信
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        final AlternateDemo alternateDemo = new AlternateDemo();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alternateDemo.loopA();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alternateDemo.loopB();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    alternateDemo.loopC();
                }
            }
        }, "C").start();
    }

}

class AlternateDemo {

    int printFlag = 1;

    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();


    public void loopA() {
        lock.lock();
        try {
            if (printFlag != 1) {
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            printFlag = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void loopB() {
        lock.lock();
        try {
            if (printFlag != 2) {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            printFlag = 3;
            conditionC.signal();
        } finally {
            lock.unlock();
        }

    }

    public void loopC() {
        lock.lock();
        try {
            if (printFlag != 3) {
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            printFlag = 1;
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }


}
