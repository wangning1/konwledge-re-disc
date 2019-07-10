package com.xml.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by wangning on 2017/8/9.
 * 当使用@XmlElemet修饰属性的时候，需要使用@XmlAccessorType
 */
@XmlRootElement(name = "sererlist")
//@XmlAccessorType(XmlAccessType.FIELD)
public class ServerList {
    private List<Server> servers;

    @XmlElement(name = "server")
    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
