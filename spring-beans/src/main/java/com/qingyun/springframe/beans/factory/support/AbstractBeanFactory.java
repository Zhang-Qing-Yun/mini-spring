package com.qingyun.springframe.beans.factory.support;

import com.qingyun.springframe.beans.BeansException;
import com.qingyun.springframe.beans.factory.BeanFactory;
import com.qingyun.springframe.beans.factory.config.BeanDefinition;

/**
 * @description： BeanFactory的抽象实现类，实现了获取bean的逻辑过程
 * @author: 張青云
 * @create: 2021-08-18 18:09
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {  // 此处应用模板方法模式
        //  先去查单例注册表
        Object singleton = getSingleton(name);
        if (singleton != null) {
            return singleton;
        }

        //  当单例注册表中不存在单例对象时，需要去获取bean的定义信息并且去创建bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);

        //  将创建的bean对象添加到单例注册表中
        registerSingleton(name, bean);
        return bean;
    }

    /**
     * 获取bean的定义信息
     * @param beanName bean的名字
     * @return 定义信息
     * @throws BeansException 获取出错时的异常
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 根据定义信息去创建bean对象
     * @param beanName bean的名字
     * @param beanDefinition bean的定义信息
     * @return bean对象
     * @throws BeansException 出错时的异常
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
