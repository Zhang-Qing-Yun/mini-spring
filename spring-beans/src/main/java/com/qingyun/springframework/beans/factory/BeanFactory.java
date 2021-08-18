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
}
