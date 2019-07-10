package com.json.main;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.json.annotationBean.Animal;
import com.json.annotationBean.Elephant;
import com.json.annotationBean.Lion;
import com.json.annotationBean.Zoo;
import com.json.bean.Country;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangning on 2017/8/9.
 */
public class AnnotationJsonMain {

    public static void main(String[] args) throws IOException {
        Zoo zoo = new Zoo();
        zoo.setName("zoo");
        zoo.setLoction("beijing");
        zoo.setIncome(12000.232);
        zoo.setOpenDate(new Date(System.currentTimeMillis()));
        List<Animal> animals = new ArrayList<Animal>();
        Lion lion = new Lion();
        lion.setName("lion");
        Elephant elephant = new Elephant();
        elephant.setName("elephant");
        animals.add(lion);
        animals.add(elephant);
        zoo.setAnimals(animals);

        String bean2JsonStr = bean2JsonStr(zoo);
        System.out.println(bean2JsonStr);

        System.out.println("----------华丽丽分割线---------------");
        Zoo jsonStr2Bean = jsonStr2Bean(bean2JsonStr);
        System.out.println(jsonStr2Bean.toString());

        String hasOtherProJsonStr = "{\n" +
                "  \"loction\" : \"beijing\",\n" +
                "  \"income\" : \"12000.23\",\n" +
                "  \"openDate\" : \"2017-08-09 11:25:27\",\n" +
                "  \"animals\" : [ {\n" +
                "    \"@class\" : \"com.json.annotationBean.Lion\",\n" +
                "    \"name\" : \"lion\",\n" +
                "    \"type\" : \"lionType\"\n" +
                "  }, {\n" +
                "    \"@class\" : \"com.json.annotationBean.Elephant\",\n" +
                "    \"name\" : \"elephant\",\n" +
                "    \"type\" : \"elephantType\"\n" +
                "  } ],\n" +
                "  \"zooName\" : \"zoo\",\n" +
                "  \"otherProperties\" : \"otherProperties\"\n" +
                "}";

        Zoo hasOtherPro =   jsonStr2Bean(hasOtherProJsonStr);
        System.out.println(hasOtherPro.any().size());
        System.out.println(bean2JsonStr(hasOtherPro));
    }


    public static String bean2JsonStr(Zoo zoo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        StringWriter stringWriter = new StringWriter();
        objectMapper.writeValue(stringWriter, zoo);
        return stringWriter.toString();
    }

    public static Zoo jsonStr2Bean(String jsonStr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //忽略没有声明的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Zoo zoo = objectMapper.readValue(jsonStr, Zoo.class);
        return zoo;
    }

}
