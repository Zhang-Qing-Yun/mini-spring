package com.qingyun.springframework.context;

import com.qingyun.springframework.beans.BeansException;
import com.qingyun.springframework.beans.factory.Aware;

/**
 * @description：
 * @author: 張青云
 * @create: 2021-08-22 12:07
 **/
public interface ApplicationContextAware extends Aware {

    /**
     * 获取所属的应用上下文ApplicationContext
     */
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
