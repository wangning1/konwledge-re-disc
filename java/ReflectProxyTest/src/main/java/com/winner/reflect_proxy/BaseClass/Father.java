package com.winner.reflect_proxy.BaseClass;

/**
 * Created by wangning on 2017/5/10.
 */
public class Father extends Person {
    protected String eyes;
    protected String nose;

    public Father(){

    }

    public Father(String eyes, String nose){
        this.eyes = eyes;
        this.nose = nose;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
    }

    public String getNose() {
        return nose;
    }

    public void setNose(String nose) {
        this.nose = nose;
    }

    protected void canSee() {
     System.out.println("-- Father has tow eyes and he can see");
    }

    protected void canSmell() {
        System.out.println("-- Father has one nose and he can smell");
    }
}
