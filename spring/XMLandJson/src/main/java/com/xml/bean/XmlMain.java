package com.xml.bean;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangning on 2017/8/8.
 */
public class XmlMain {

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setAge(12);
        customer.setId(1);
        customer.setName("test");
        String str = bean2Xml(customer);
        System.out.println(str);


        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<customer id=\"1\">\n" +
                "    <age>12</age>\n" +
                "    <name>test</name>\n" +
                "</customer>";

        Customer result = xml2Bean(xmlStr);
        System.out.println(result.toString());


    }


    public static String bean2Xml(Customer customer) {
        Writer writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(customer, System.out);
            marshaller.marshal(customer, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return  writer.toString();
    }

    public static Customer xml2Bean(String xmlStr) {
        Customer customer = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            customer = (Customer) unmarshaller.unmarshal(new InputSource(new StringReader(xmlStr)));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return customer;
    }

}
