package com.wn.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangning on 2017/3/16.
 * 业务场景：三个窗口去卖100章票
 *
 * 用于解决多线程安全问题的方式
 * 1.synchronized 隐式锁
 *   1）同步方法
 *   2）同步代码块
 * 2.Lock 显示锁
 *   注意：在上锁之后记得释放锁
 */
public class TestLock {
    public static void main(String[] args) {
//        TicketDemo td = new TicketDemo();
        TicketDemoForLock td = new TicketDemoForLock();
        new Thread(td, "一号窗口").start();
        new Thread(td, "二号窗口").start();
        new Thread(td, "三号窗口").start();
    }
}


class TicketDemo implements Runnable {

    private int ticket = 100;

    public void run() {
        while (true) {
            synchronized (this){
                if (ticket > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    System.out.println("当前窗口" + Thread.currentThread() + "完成售票" + --ticket);
                }
            }
        }
    }
}

class TicketDemoForLock implements Runnable{

    private int ticket = 100;

    private Lock lock = new ReentrantLock();

    public void run() {
        while (true){
            lock.lock();
            try {
                if (ticket > 0){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("当前窗口" + Thread.currentThread() + "完成售票" + --ticket);
                }
            }finally {
                lock.unlock();
            }
        }
    }
}
