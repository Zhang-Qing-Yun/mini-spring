package com.qingyun.springframework.context;

/**
 * @description： 时间发布者
 * @author: 張青云
 * @create: 2021-08-23 00:53
 **/
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     */
    void publishEvent(ApplicationEvent event);
}
