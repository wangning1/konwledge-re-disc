package com.json.annotationBean;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangning on 2017/8/9.
 */
public class Zoo {

    //使用别名
    @JsonProperty(value = "zooName")
    private String name;
    private String loction;
//    @JsonSerialize(using = CustomDoubleSerialize.class)
    private Double income;
    //通过注解指定格式
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date openDate;
    private List<Animal> animals;

    private Map<String,Object> otherProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoction() {
        return loction;
    }

    public void setLoction(String loction) {
        this.loction = loction;
    }

    //使用自定义序列化器
    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @JsonSerialize(using = CustomDoubleSerialize.class)
    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return otherProperties;
    }

    @JsonAnySetter
    public void set(String key, Object valie) {
        otherProperties.put(key, valie);
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "name='" + name + '\'' +
                ", loction='" + loction + '\'' +
                ", openDate=" + openDate +
                ", income=" + income +
                ", animals=" + animals +
                '}';
    }
}
