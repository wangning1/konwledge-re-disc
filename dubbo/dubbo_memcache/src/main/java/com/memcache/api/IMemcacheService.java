package com.memcache.api;

/**
 * Created by wangning on 2017/7/7.
 */
public interface IMemcacheService {
    Object get(String var1);

    void set(String var1, Object var2, int var3);

    void delete(String var1);

    boolean add(String var1, Object var2, int var3);

    long incr(String var1, long var2);

    long incr(String var1, long var2, long var4);

}
