package com.qingyun.springframework.beans.factory.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @description： 实现了根据BeanDefinition去创建bean的能力
 * @author: 張青云
 * @create: 2021-08-18 18:29
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    //  指定实例化方式的策略，默认使用JDK反射的方式
    private InstantiationStrategy instantiation = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor usedConstructor = null;
        Constructor[] constructors = beanDefinition.getBeanClass().getDeclaredConstructors();

        for (Constructor ctor: constructors) {
            if (args != null && ctor.getParameterTypes().length == args.length) {
                Class[] parameterTypes = ctor.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (parameterTypes[i] != args[i].getClass()) {
                        break;
                    }
                }
                usedConstructor = ctor;
                break;
            }
        }
        return instantiation.instantiate(beanName, beanDefinition, usedConstructor, args);
    }
}
