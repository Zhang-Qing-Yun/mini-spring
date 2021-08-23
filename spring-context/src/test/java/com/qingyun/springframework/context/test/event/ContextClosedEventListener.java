package com.qingyun.springframework.context.test.event;

import com.qingyun.springframework.context.ApplicationListener;
import com.qingyun.springframework.context.event.ContextClosedEvent;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 10:10
 **/
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }

}
