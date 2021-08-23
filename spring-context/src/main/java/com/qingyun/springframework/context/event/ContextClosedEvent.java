package com.qingyun.springframework.context.event;

/**
 * @description： 关闭应用上下文
 * @author: 張青云
 * @create: 2021-08-23 00:35
 **/
public class ContextClosedEvent extends ApplicationContextEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}