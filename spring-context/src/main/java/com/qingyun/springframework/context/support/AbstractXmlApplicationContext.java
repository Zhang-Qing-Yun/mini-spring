package com.qingyun.springframework.context.support;

import com.qingyun.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.qingyun.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 从Xml文件中加载Bean定义信息
 * @author: 張青云
 * @create: 2021-08-21 18:37
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations && configLocations.length != 0){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    /**
     * 获取XML配置文件的路径地址
     */
    protected abstract String[] getConfigLocations();
}