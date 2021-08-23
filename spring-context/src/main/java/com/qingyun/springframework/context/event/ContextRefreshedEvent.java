package com.qingyun.springframework.context.event;

/**
 * @description： 刷新应用上下文
 * @author: 張青云
 * @create: 2021-08-23 00:36
 **/
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }

}
