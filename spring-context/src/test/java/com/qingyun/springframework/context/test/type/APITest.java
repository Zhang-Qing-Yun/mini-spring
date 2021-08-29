package com.qingyun.springframework.context.test.type;

import com.qingyun.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-28 21:59
 **/
public class APITest {
    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-test.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("测试结果：" + husband);
    }

}
