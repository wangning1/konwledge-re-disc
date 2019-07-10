package com.json.annotationBean;

/**
 * Created by wangning on 2017/8/9.
 */
public class Elephant extends Animal {
    private String name;

    public String getName(){
        return name;
    }

    public String getType(){
        return "elephantType";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Elephant{" +
                "name='" + name + '\'' +
                "type='" + type + '\'' +
                '}';
    }
}
