package com.qingyun.springframework.context.test.event;

import com.qingyun.springframework.context.ApplicationListener;
import com.qingyun.springframework.context.event.ContextRefreshedEvent;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 10:11
 **/
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }

}
