package com.winner.annotionTest.testAnnotion;

import com.winner.annotionTest.annotions.FruitColor;
import com.winner.annotionTest.annotions.FruitName;
import com.winner.annotionTest.annotions.FruitProvider;

import java.lang.reflect.Field;

/**
 * Created by wangning on 2017/5/9.
 */
public class FruitInfoUtil {

    public  static void getFruitInfo(Class<?> clazz) {
        String strFruitName = "水果的名称：";
        String strFruitColor = "水果的颜色：";
        String strFruitProvider = "水果的供应商：";

        Field[] fields = clazz.getDeclaredFields();
        System.out.println(fields.length);

        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor += fruitColor.fruitColor();
                System.out.println(strFruitColor);
            } else if (field.isAnnotationPresent(FruitName.class)) {
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName += fruitName.value();
                System.out.println(strFruitName);
            } else if (field.isAnnotationPresent(FruitProvider.class)) {
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider += "编号是：" + fruitProvider.id() + ";供应商名称：" + fruitProvider.name() + ";供应商地址：" + fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }
    }

}
