package com.qingyun.springframework.context.event;

import com.qingyun.springframework.beans.factory.BeanFactory;
import com.qingyun.springframework.context.ApplicationEvent;
import com.qingyun.springframework.context.ApplicationListener;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-23 01:03
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }

}
