package com.qingyun.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.PropertyValue;
import com.qingyun.springframework.beans.factory.PropertyValues;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.config.BeanReference;

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
            //  实例化bean
            bean = createBeanInstance(beanDefinition, beanName, args);
            //  对bean对象进行属性填充
            if (beanDefinition.getPropertyValues() != null &&
                    beanDefinition.getPropertyValues().getPropertyValues().length != 0) {
                applyPropertyValues(beanName, bean, beanDefinition);
            }
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        return bean;
    }

    /**
     * 创建bean实例
     */
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

    /**
     * 属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue: propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    //  TODO 没有解决循环依赖问题
                    // A 依赖 B，获取 B 的实例
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public void setInstantiation(InstantiationStrategy instantiation) {
        this.instantiation = instantiation;
    }
}
