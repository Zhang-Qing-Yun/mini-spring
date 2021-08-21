package com.qingyun.springframework.context.test;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.qingyun.springframework.beans.factory.PropertyValue;
import com.qingyun.springframework.beans.factory.PropertyValues;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-21 21:15
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "字节跳动(改)"));
    }

}
