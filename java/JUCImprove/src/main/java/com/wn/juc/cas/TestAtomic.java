package com.wn.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangning on 2017/3/16.
 * 技巧：当数据量小时，程序运行较快，并发性问题不容易出现，可以使用休眠的方式，来模拟延迟
 * 现象：
 *        i++ 的原子性问题：i++ 的操作实际上分为三个步骤“读-改-写”
 * 		  int i = 10;
 * 		  i = i++; //10
 *
 * 		  int temp = i;
 * 		  i = i + 1;
 * 		  i = temp;
 *        volatile可以保证内存的可见性，但不保证变量的原子性
 *        通过使用juc包下的原子类可以保证在多线程并发的操作时保证复合操作的原子性
 *
 * 结论：
 *     原子变量：在java.util.concurrent.atomic包下一些原子变量
 *              1.volatile 保证内存可见性
 *              2.CAS算法保证数据变量的原子性
 *                CAS算法是硬件对于并发操作的支持
 *                CAS包含三个操作数
 *                V 内存值
 *                A 预估值
 *                B 更新值
 *                当V == A时，V = B；否则，不会执行任何操作
 */
public class TestAtomic {
    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }
    }
}

class AtomicDemo implements Runnable {

//     private volatile int number = 0;
       private AtomicInteger number = new AtomicInteger(0);

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println(Thread.currentThread().getName() + " " + getNumber());
    }

    //使用atomicInteger的getAndIncrement方法增加并且获取值
    public int getNumber(){return  number.getAndIncrement();}
}
