package com.qingyun.springframework.aop.test.threeCache;

import com.qingyun.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-26 20:54
 **/
public class APITest {
    @Test
    public void test_circular() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:three-cache.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        Wife wife = applicationContext.getBean("wife", Wife.class);
        System.out.println("老公的媳妇：" + husband.queryWife());
        System.out.println("媳妇的老公：" + wife.queryHusband());
    }
}
