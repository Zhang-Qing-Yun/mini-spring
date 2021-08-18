package com.qingyun.springframework.beans.factory.config;

/**
 * @description： Bean的定义信息，对应于spring源码中解析xml文件后获取到的Bean定义信息
 * @author: 張青云
 * @create: 2021-08-18 16:52
 **/
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
