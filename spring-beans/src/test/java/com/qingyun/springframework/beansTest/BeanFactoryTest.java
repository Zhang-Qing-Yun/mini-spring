package com.qingyun.springframework.beansTest;

import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-18 18:52
 **/
public class BeanFactoryTest {
    @Test
    public void beanTest() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
//        // 4.第二次获取 bean from Singleton
//        Object object2 = beanFactory.getBean("object");
//        assert object == object2;
        System.out.println(userService.getUser());
    }
}
