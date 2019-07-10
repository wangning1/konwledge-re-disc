package com.xml.bean;

import com.xml.util.XmlUtil;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangning on 2017/8/8.
 */
public class UtilMain {

    public static void main(String[] args) {
//        List<JAXBElement<Customer>> jaxbElementList = new ArrayList<JAXBElement<Customer>>();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("one");
        customer1.setAge(1);





        String customerStr = XmlUtil.toXmlString(customer1);
        System.out.println(customerStr);

        System.out.println("----------------华丽丽分割线，string 转 bean-------------------");
        Customer customer = XmlUtil.toObject(customerStr, Customer.class);
        System.out.println(customer.toString());
    }

}
