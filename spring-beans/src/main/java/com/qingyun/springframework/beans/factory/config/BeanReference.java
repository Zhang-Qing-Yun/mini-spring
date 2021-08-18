package com.qingyun.springframework.beans.factory.config;

/**
 * @description： bean对象对另一个bean对象的引用
 * @author: 張青云
 * @create: 2021-08-18 23:56
 **/
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
