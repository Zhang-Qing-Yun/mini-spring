package com.qingyun.springframework.beansTest;

import com.qingyun.springframework.beans.factory.PropertyValue;
import com.qingyun.springframework.beans.factory.PropertyValues;
import com.qingyun.springframework.beans.factory.config.BeanDefinition;
import com.qingyun.springframework.beans.factory.config.BeanReference;
import com.qingyun.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-18 18:52
 **/
public class BeanFactoryTest {
    @Test
    public void beanTest1() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 com.qingyun.springframework.aop.test.bean
        BeanDefinition beanDefinition = new BeanDefinition(Object.class);
        beanFactory.registerBeanDefinition("object", beanDefinition);

        // 3.第一次获取 com.qingyun.springframework.aop.test.bean
        Object object = beanFactory.getBean("object");
        // 4.第二次获取 com.qingyun.springframework.aop.test.bean from Singleton
        Object object2 = beanFactory.getBean("object");
        assert object == object2;
    }

    @Test
    public void beanTest2() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 com.qingyun.springframework.aop.test.bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3.获取 com.qingyun.springframework.aop.test.bean
        UserService userService = (UserService) beanFactory.getBean("userService");

//        System.out.println(userService.getUser());
    }

    @Test
    public void beanTest3() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

    }
}
