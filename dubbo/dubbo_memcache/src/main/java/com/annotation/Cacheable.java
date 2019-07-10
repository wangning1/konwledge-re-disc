package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wangning on 2017/7/12.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable {

    String value();
    String condition() default "";

    String fieldKey() default "";
    /**
     * key的过期时间，单位秒
     * <= 0 永不过期
     * @return
     */
    int expireTime() default 0;
}
