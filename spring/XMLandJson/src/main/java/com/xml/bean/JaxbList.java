package com.xml.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

/**
 * Created by wangning on 2017/8/9.
 */
@XmlRootElement(name = "jaxbList")
@XmlAccessorType(XmlAccessType.PROPERTY)
//@XmlSeeAlso({Customer.class})
public class JaxbList<T>  {
    protected List<T> list;

    public JaxbList(){

    }

    public JaxbList(List<T> list){
        this.list = list;
    }

    @XmlElement(name = "list")
    public List<T> getList(){
        return list;
    }

    public void setList(List<T> list){
        this.list = list;
    }

}
