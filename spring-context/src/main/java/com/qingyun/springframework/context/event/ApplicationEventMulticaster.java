package com.qingyun.springframework.context.event;

import com.qingyun.springframework.context.ApplicationEvent;
import com.qingyun.springframework.context.ApplicationListener;

/**
 * @description： 事件广播器
 * @author: 張青云
 * @create: 2021-08-23 00:37
 **/
public interface ApplicationEventMulticaster {
    /**
     * 增加一个事件监听器
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 删除一个事件监听器
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件，让监听该事件的所有监听器感知到，从而执行对应的操作
     */
    void multicastEvent(ApplicationEvent event);
}
