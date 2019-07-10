package com.wn.juc.cas;

/**
 * Created by wangning on 2017/3/16.
 * 简单模拟Compareandswap
 *
 * 实现的关键：
 * 比如将 内存中1 改变成2
 *     1.先将expectedValue 为1
 *     2.从内存中获取oldValue
 *     3.比较expectedValue == oldValue ? 将value置为newValue : 不做操作
 *     这样的已修改的话，如果同时有另一个进程获取到的oldValue和expecedValue不一样，将会不做修改
 *     因此达到只能一个线程修改的目的
 *
 *
 */
public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 21));
                    System.out.println("--isSuccess:" + b + " " +cas.get());
                }
            }).start();
        }
    }
}

class CompareAndSwap {

    private int value;

    //获取内存值
    public synchronized int get() {
        return this.value;
    }

    //比较
    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (expectedValue == oldValue) {
            this.value =  newValue;
        }
        return oldValue;
    }

    //设置
    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }


}
