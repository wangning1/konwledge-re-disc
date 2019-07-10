package com.web.CustomerParse;

import com.web.model.CustomerTag;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * Created by wangning on 2017/6/12.
 */
public class CustomerTagDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private final static Logger logger = LoggerFactory.getLogger(CustomerTagDefinitionParser.class);

    @Override
    protected Class<?> getBeanClass(Element element) {
        return CustomerTag.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {

        //从element中解析到对应的类
        String tagId = element.getAttribute("id");
        String tagName = element.getAttribute("tagName");

        logger.info("--[parser:tagId]--" + tagId);
        logger.info("--[parser:tagName]--" + tagName);
        //将数据放到BeanDefinitionBuilder中，待到完成所有bean的解析后统一注册到beanFactory中

        if(StringUtils.isNotBlank(tagId)){
            builder.addPropertyValue("tagId", tagId);
        }

        if(StringUtils.isNotBlank(tagName)){
            builder.addPropertyValue("tagName", tagName);
        }

    }
}
