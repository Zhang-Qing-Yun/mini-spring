package com.qingyun.springframework.context;

import com.qingyun.springframework.beans.BeansException;

/**
 *
 * @author: 張青云
 * @create: 2021-08-21 17:25
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;

    /**
     * 注册虚拟机钩子方法
     */
    void registerShutdownHook();

    /**
     * 手动执行关闭
     */
    void close();
}
