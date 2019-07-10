package com.winner.DynamicProxy.JDKProxy;

/**
 * Created by wangning on 2017/7/10.
 */
public class ElectricCar implements Rechargable, Vehicle {

    public void dirve() {
      System.out.println("ElectricCar can drive...");
    }

    public String reCharge(Long chargeTime) {
        System.out.println("ElectricCar can charge...");
        return "charge 30 minute  is ok.";
    }

    public void changColor(){
        System.out.println("ElectricCar can change color...");
    }
}
