package com.web.controller;

import com.xml.bean.Address;
import com.xml.bean.Server;
import com.xml.bean.ServerList;
import com.xml.util.XmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangning on 2017/8/9.
 */
@RequestMapping(value = "xml")
@Controller
public class XmlReturnController {

    /**
     * 可以通过produces指定响应的类型
     * @return
     */
    @RequestMapping(value = "xmlReturn", produces = {"application/json", "application/xml"})
    @ResponseBody
    public ServerList xmlReturn() {
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
        return serverList;
    }


}
