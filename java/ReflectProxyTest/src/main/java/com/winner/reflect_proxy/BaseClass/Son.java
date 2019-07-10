package com.winner.reflect_proxy.BaseClass;

/**
 * Created by wangning on 2017/5/10.
 */
public class Son extends Father implements Glassess{

    private String mouse;

    public String getMouse() {
        return mouse;
    }

    public void setMouse(String mouse) {
        this.mouse = mouse;
    }

    public Son() {
    }

    public Son(String eyes, String nose) {
        super(eyes, nose);
    }

    @Override
    public void canSee() {
         System.out.println("-- son has som wrong with eye, he cann't see clearly.");
        strengthenEyesight();
    }

    public static void main(String[] args){
        Son son = new Son("small eye", "big nose");
        System.out.println(son.eyes);
    }

    public void strengthenEyesight() {
        System.out.println("son has a pair of glassess, he can see clearly.");
    }
}
