package com.qingyun.springframework.beansTest;

import com.qingyun.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.qingyun.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.qingyun.springframework.core.io.DefaultResourceLoader;
import org.junit.Test;


/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-20 11:09
 **/
public class SourceLoadTest {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(new DefaultResourceLoader().getResource("classpath:spring.xml"));

        // 3. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }
}
