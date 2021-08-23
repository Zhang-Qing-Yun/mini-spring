package com.qingyun.springframework.context;

import java.util.EventListener;

/**
 * @description： 事件监听器
 * @author: 張青云
 * @create: 2021-08-23 00:38
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     * 监听的事件发生时触发的操作
     */
    void onApplicationEvent(E event);

}
