package com.qingyun.springframework.beans.factory;

import com.qingyun.springframework.beans.BeansException;

/**
 * @description： 整个spring的根接口，bean的工厂，负责创建、维护以及供客户端获取bean
 * @author: 張青云
 * @create: 2021-08-18 16:48
 **/
public interface BeanFactory {
    /**
     * 从IOC容器中获取bean对象
     * @param name 该bean对象注册时的名字
     * @return bean对象
     * @throws BeansException 出错时抛出该异常
     */
    Object getBean(String name) throws BeansException;

    /**
     * 从IOC容器中获取bean对象并且指定创建对象使用的构造器参数
     * @param name 该bean对象注册时的名字
     * @param args 创建该bean时的构造器参数，倘若无该参数对应的构造器则报错，如：无空参构造器但是args==null时报错
     * @return bean对象
     * @throws BeansException 出错时抛出该异常
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 根据bean的名字和类型来获取bean对象
     * @param name bean的名字
     * @param requiredType bean的类型
     * @param <T> bean的类型
     * @return bean
     * @throws BeansException 出错时抛出该异常
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
