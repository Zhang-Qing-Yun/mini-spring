package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;

/**
 * @description： bean定义信息的注册表
 * @author: 張青云
 * @create: 2021-08-18 18:38
 **/
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中添加bean的定义信息
     * @param beanName bean的名字
     * @param beanDefinition bean的定义信息
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 获取bean的定义信息
     * @param beanName bean的名字
     * @return bean的定义信息
     * @throws BeansException 获取失败时抛出异常
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
