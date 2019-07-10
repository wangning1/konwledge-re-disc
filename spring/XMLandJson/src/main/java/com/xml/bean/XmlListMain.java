package com.xml.bean;

import com.xml.util.XmlUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangning on 2017/8/9.
 */
public class XmlListMain {

    public static void main(String[] args) {
          genericList2Xml();
    }

    /**
     * 使用泛型
     */
    public static void genericList2Xml() {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        JaxbList<String> stringJaxbList = new JaxbList<String>();
        stringJaxbList.setList(list);
        String list2xmlStr = XmlUtil.toXmlString(stringJaxbList);
        System.out.println(list2xmlStr);

        XmlUtil.toObject(list2xmlStr,String.class);
    }

    public static void serverList2Xml() {
        ServerList serverList = new ServerList();
        List<Server> list = new ArrayList<Server>();
        Server server = new Server();
        server.setName("one");
        server.setLoad(1);
        server.setTerminal("1");
        Address address = new Address();
        address.setName("add1");
        address.setIp("12.12.12.12");
        address.setPort("90");
        server.setAddress(address);
        list.add(server);
        serverList.setServers(list);

        String list2Xml = XmlUtil.toXmlString(serverList);
        System.out.println(list2Xml);

        System.out.println("------------分割线------------------");
        ServerList xml2List = XmlUtil.toObject(list2Xml, ServerList.class);
        System.out.println(xml2List.getServers().size());


    }

}
