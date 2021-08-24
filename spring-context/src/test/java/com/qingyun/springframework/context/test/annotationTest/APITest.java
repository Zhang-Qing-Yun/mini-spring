package com.qingyun.springframework.context.test.annotationTest;

import com.qingyun.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-24 18:50
 **/
public class APITest {
    @Test
    public void test_property() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService);
    }

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:ScanTest.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
