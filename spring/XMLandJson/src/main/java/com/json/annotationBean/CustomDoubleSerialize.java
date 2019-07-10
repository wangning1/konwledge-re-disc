package com.json.annotationBean;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by wangning on 2017/8/9.
 */
public class CustomDoubleSerialize extends JsonSerializer<Double> {

    private DecimalFormat df = new DecimalFormat("##.00");

    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
       jsonGenerator.writeString(df.format(aDouble));
    }
}
