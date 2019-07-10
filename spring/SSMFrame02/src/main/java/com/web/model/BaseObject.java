package com.web.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by wangning on 2017/3/10.
 */
public class BaseObject implements Serializable {
    private static final long serialVersionUID = 9098429294327164425L;

    public BaseObject(){

    }

    public String toString(){return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);}

    public boolean equals(Object o){return EqualsBuilder.reflectionEquals(this, o);}

    public int hashCode(){return HashCodeBuilder.reflectionHashCode(this);}

}
