package com.web.CustomerParse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by wangning on 2017/6/12.
 */
public class CustomerTagHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("customerTag", new CustomerTagDefinitionParser());
    }

}
