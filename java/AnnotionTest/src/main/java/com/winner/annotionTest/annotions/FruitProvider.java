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
public @interface FruitProvider {

    /**
     * 供应商编号
     * @return
     */
    public int id() default -1;


    /**
     * 供应商名称
     * @return
     */
    public String name() default "";


    /**
     * 供应商地址
     * @return
     */
    public String address() default "";
}
