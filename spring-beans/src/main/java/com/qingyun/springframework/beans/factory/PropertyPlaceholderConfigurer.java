package com.qingyun.springframework.beans.factory;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.qingyun.springframework.core.io.DefaultResourceLoader;
import com.qingyun.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @description： 处理Bean定义信息中的占位符信息
 * @author: 張青云
 * @create: 2021-08-24 18:02
 **/
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    //  默认占位符的前缀
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    //  默认占位符的后缀
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIdx, stopIdx + 1, propVal);
                        //  这里并没有删除原属性，但是在进行属性填充的时候会使用修改后的属性值覆盖原来的属性值
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
