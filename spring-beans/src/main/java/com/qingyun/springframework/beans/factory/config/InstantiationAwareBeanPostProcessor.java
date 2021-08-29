package com.qingyun.springframework.beans.factory.config;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.PropertyValues;

/**
 * @description： 扩展BeanPostProcessor
 * @author: 張青云
 * @create: 2021-08-24 01:01
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 Bean 对象执行实例化方法之前，执行此方法
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行实例化方法之后，执行此方法
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;

    /**
     * 在 Spring 中由 SmartInstantiationAwareBeanPostProcessor#getEarlyBeanReference 提供
     */
    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
