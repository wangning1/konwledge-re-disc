package com.wn.juc.producer_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangning on 2017/3/17.
 */
public class TestProductorAndConsumerForLock {

    public static void main(String[] a) {
        StoreForLock storeForLock = new StoreForLock();
        ProductForLock pf = new ProductForLock(storeForLock);
        ConsumerForLock cf = new ConsumerForLock(storeForLock);

        new Thread(pf,"生产者A").start();
        new Thread(cf,"消费者B").start();

        new Thread(pf,"生产者C").start();
        new Thread(cf,"消费者D").start();
    }
}

class StoreForLock {
    private int product = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    //进货
    public void stock() {
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("库存已满！！！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                System.out.println(Thread.currentThread().getName() + "处的进货，现在库存" + ++product);
                condition.signalAll();

        } finally {
            lock.unlock();
        }
    }

    //销售
    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("货物已售完！！！");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                System.out.println(Thread.currentThread().getName() + "买了货，现在库存" + --product);
                condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class ProductForLock implements Runnable {

    private StoreForLock storeForLock;

    public ProductForLock(StoreForLock storeForLock) {
        this.storeForLock = storeForLock;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storeForLock.stock();
        }
    }
}


class ConsumerForLock implements Runnable {

    private StoreForLock storeForLock;

    ConsumerForLock(StoreForLock storeForLock) {
        this.storeForLock = storeForLock;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            storeForLock.sale();
        }
    }
}