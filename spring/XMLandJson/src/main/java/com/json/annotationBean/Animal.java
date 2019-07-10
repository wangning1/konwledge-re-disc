package com.json.annotationBean;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by wangning on 2017/8/9.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,include = JsonTypeInfo.As.PROPERTY, property = "@class")
//@JsonSubTypes({@JsonSubTypes.Type(value = Lion.class,name="lion"),@JsonSubTypes.Type(value = Elephant.class,name="elephant")})
public abstract class Animal {
    String name;
    String type;
}
