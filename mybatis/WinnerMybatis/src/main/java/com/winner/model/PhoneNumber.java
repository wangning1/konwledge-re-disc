package com.winner.model;

/**
 * Created by wangning on 2017/7/13.
 */
public class PhoneNumber {
    private String countryCode;
    private String statCode;
    private String number;

    public PhoneNumber(String countryCode, String statCode, String number){
        this.countryCode = countryCode;
        this.statCode = statCode;
        this.number = number;
    }

    public PhoneNumber(String phoneNumber){
        String[] numberArr = phoneNumber.split("-");
        if(numberArr.length >= 0){
            this.countryCode = numberArr[0];
        }
        if(numberArr.length >= 1){
            this.statCode = numberArr[1];
        }
        if(numberArr.length >= 2){
            this.number = numberArr[2];
        }
    }

    public String getAsString()
    {
        return countryCode + "-" + statCode + "-" + number;
    }
}
