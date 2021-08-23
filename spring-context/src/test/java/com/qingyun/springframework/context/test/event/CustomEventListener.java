package com.qingyun.springframework.context.test.event;

import com.qingyun.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 10:09
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }

}
