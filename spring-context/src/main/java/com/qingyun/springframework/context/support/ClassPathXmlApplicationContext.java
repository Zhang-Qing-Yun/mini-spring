package com.qingyun.springframework.context.support;

import com.qingyun.springframework.beans.BeansException;

/**
 * @description： XML文件应用上下文
 * @author: 張青云
 * @create: 2021-08-21 19:33
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    //  配置文件的路径地址
    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }

}
