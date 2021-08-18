package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description： 根据beanDefinition实例化对象的策略
 * @author: 張青云
 * @create: 2021-08-18 21:09
 **/
public interface InstantiationStrategy {

    /**
     * 根据beanDefinition实例化一个对象的具体操作
     * @param beanName 待实例化的bean的名字
     * @param beanDefinition bean的定义信息
     * @param ctor 通过bean的哪个构造器实例化bean，为null则使用空参构造器
     * @param args 构造器参数
     * @return bean对象
     * @throws BeansException 出错时抛出异常
     */
    Object instantiate(String beanName, BeanDefinition beanDefinition,
                       Constructor ctor, Object[] args) throws BeansException;
}
