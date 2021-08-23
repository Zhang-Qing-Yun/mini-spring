package com.qingyun.springframework.context.event;

import com.qingyun.springframework.context.ApplicationContext;
import com.qingyun.springframework.context.ApplicationEvent;

/**
 * @description： 应用上下文相关的事件
 * @author: 張青云
 * @create: 2021-08-23 00:34
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * 获取应用上下文
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }

}
