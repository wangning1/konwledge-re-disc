package com.json.annotationBean;

/**
 * Created by wangning on 2017/8/9.
 */
public class Lion extends Animal {

    private String name;



    public String getName(){
        return name;
    }

    public String getType(){
        return "lionType";
    }

    @Override
    public String toString() {
        return "Lion{" +
                "name='" + name + '\'' +
                "type='" + type + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
