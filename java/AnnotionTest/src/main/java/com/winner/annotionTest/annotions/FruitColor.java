package com.winner.annotionTest.annotions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangning on 2017/5/9.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {

    public enum Color {BLUE, Green, Red}

    ;

    public Color fruitColor() default Color.Red;
}
