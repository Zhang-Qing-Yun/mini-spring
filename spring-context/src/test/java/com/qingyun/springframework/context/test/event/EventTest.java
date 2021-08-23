package com.qingyun.springframework.context.test.event;

import com.qingyun.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 10:12
 **/
public class EventTest {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));

        applicationContext.registerShutdownHook();
    }
}
