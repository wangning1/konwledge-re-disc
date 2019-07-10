package com.winner.DynamicProxy.CGLIBProxy;

/**
 * Created by wangning on 2017/7/10.
 */
public class Programmer implements Coder {

    public String learning(String book){
        System.out.println(" programmer is reading book.");
        return "learning";
    }

    public void coding() {
        System.out.println("Programmer is also coder.");
    }
}
