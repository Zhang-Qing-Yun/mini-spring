package com.qingyun.springframework.beans.factory.config;

import com.qingyun.springframework.beans.factory.PropertyValues;

/**
 * @description： Bean的定义信息，对应于spring源码中解析xml文件后获取到的Bean定义信息
 * @author: 張青云
 * @create: 2021-08-18 16:52
 **/
public class BeanDefinition {
    //  bean的类型
    private Class beanClass;

    //  bean的属性信息
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
