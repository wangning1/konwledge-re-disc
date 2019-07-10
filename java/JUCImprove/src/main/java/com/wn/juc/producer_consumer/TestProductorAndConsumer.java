package com.wn.juc.producer_consumer;

/**
 * Created by wangning on 2017/3/16.
 * 生产者和消费者模拟：传统的方式线程通信
 * <p>
 * 现象： 1.当使用else时，当生产者执行延时，执行较慢的时候，当消费者执行完后，生产者执行的线程不会被唤醒，程序不会被结束，
 * 解决办法：去掉else，通过自身的notifyAll来唤醒
 * <p>
 * 2.使用if ,当存在多个生产者生产或者多个消费者消费，存在虚假唤醒的情况
 * 的进货,库存-31
 * 来自生产者A的进货,库存-30
 * 来自生产者C的进货,库存-29
 * 来自生产者A的进货,库存-28
 * <p>
 * 解决办法：将if通过while替换掉，通过循环的方式进行验证是否满足条件
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Store store = new Store();

        Productor productor = new Productor(store);
        Consumer consumer = new Consumer(store);

        new Thread(productor, "生产者A").start();
        new Thread(consumer, "消费者B").start();

        new Thread(productor, "生产者C").start();
        new Thread(consumer, "消费者D").start();

    }

}

class Store {
    private int product = 0;

    //进货
    public synchronized void stock() {
//        if (product >= 1) {
        while (product >= 1) {
            System.out.println("货物已满~~");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        else {
//            System.out.println("来自" + Thread.currentThread().getName() + "的进货,库存" + (++product));
//            this.notifyAll();
//        }
        System.out.println("来自" + Thread.currentThread().getName() + "的进货,库存" + (++product));
        this.notifyAll();

    }

    //销售
    public synchronized void sale() {
//        if (product <= 0) {
        while (product <= 0) {
            System.out.println("货物已售罄~~");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        else {
//            System.out.println("销售给" + Thread.currentThread().getName() + "库存为" + (--product));
//            this.notifyAll();
//        }
        System.out.println("销售给" + Thread.currentThread().getName() + "库存为" + (--product));
        this.notifyAll();
    }
}

//生产者
class Productor implements Runnable {

    private Store store;

    public Productor(Store store) {
        this.store = store;
    }

    public void run() {

        for (int i = 0; i <= 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            store.stock();
        }
    }
}


//消费者
class Consumer implements Runnable {

    private Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 0; i <= 20; i++) {
            store.sale();
        }
    }
}