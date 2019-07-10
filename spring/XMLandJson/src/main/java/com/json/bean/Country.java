package com.json.bean;

import java.util.List;

/**
 * Created by wangning on 2017/8/9.
 */
public class Country {
    private String name;
    private List<Provice> provices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Provice> getProvices() {
        return provices;
    }

    public void setProvices(List<Provice> provices) {
        this.provices = provices;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", provices=" + provices +
                '}';
    }
}
