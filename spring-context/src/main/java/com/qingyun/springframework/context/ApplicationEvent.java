package com.qingyun.springframework.context;

import java.util.EventObject;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 00:32
 **/
public class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
