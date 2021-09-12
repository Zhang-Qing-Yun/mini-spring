package com.qingyun.springframework.context.support;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 完成BeanFactory的创建和Bean定义信息的加载注册
 * @author: 張青云
 * @create: 2021-08-21 18:35
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        //  加载bean的定义信息
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    //  向容器中注册一个BeanDefinition
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    //  再次实例化容器中所有的Bean
    public void reInstantiateSingletons() {
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

}
