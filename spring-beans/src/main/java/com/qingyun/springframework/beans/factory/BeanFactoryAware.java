package com.qingyun.springframework.beans.factory;

import com.qingyun.springframework.beans.BeansException;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-22 12:01
 **/
public interface BeanFactoryAware extends Aware {

    /**
     * 获取BeanFactory
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
