package com.winner.annotionTest.testAnnotion;

import com.winner.annotionTest.annotions.FruitColor;
import com.winner.annotionTest.annotions.FruitName;
import com.winner.annotionTest.annotions.FruitProvider;

/**
 * Created by wangning on 2017/5/9.
 */
public class Apple {

    @FruitName("apple")
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.BLUE)
    private String appleColor;

    @FruitProvider(id = 1, name = "红富士", address = "陕西")
    private String appleProvider;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}
