package com.qingyun.springframework.beans.factory;

import com.qingyun.springframework.beans.BeansException;

import java.util.Map;

/**
 * @description： 扩展bean工厂接口的接口
 * @author: 張青云
 * @create: 2021-08-20 11:20
 **/
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 Bean 实例
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
