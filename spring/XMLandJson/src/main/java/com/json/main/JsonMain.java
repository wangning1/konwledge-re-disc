package com.json.main;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.json.bean.Country;
import com.json.bean.Provice;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangning on 2017/8/9.
 */
public class JsonMain {

    public static void main(String[] args) throws IOException {
        Provice provice = new Provice();
        provice.setName("beijing");
        provice.setLocation("north");
        List<Provice> provices = new ArrayList<Provice>();
        provices.add(provice);
        Country country = new Country();
        country.setName("RPC");
        country.setProvices(provices);
        String bean2JsonStr = bean2Json(country);
        System.out.println(bean2JsonStr);

        System.out.println("--------------华丽丽分割线-----------------");
        Country json2Bean = json2Bean(bean2JsonStr);
        System.out.println(json2Bean.toString());

    }

    /**
     * bean序列成json
     * @param country
     * @return
     * @throws IOException
     */
    public static String bean2Json(Country country) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //增加可读性
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, country);
        return stringWriter.toString();
    }

    /**
     * json 反序列化成bean
     * @param json
     * @return
     */
    public static Country json2Bean(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //忽略没有声明的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Country country = objectMapper.readValue(json,Country.class);
        return country;
    }

}
