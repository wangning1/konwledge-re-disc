package com.xml.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by wangning on 2017/8/9.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Server {

    @XmlAttribute(name="name")
    private String name;
    @XmlAttribute(name="load")
    private Integer load;
    @XmlAttribute(name="terminal")
    private String terminal;
    @XmlElement(name="address")
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
