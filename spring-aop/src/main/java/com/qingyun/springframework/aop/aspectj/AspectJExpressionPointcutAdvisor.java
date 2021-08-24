package com.qingyun.springframework.aop.aspectj;

import com.qingyun.springframework.aop.Pointcut;
import com.qingyun.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @description： 把切点 pointcut、拦截方法 advice 和具体的拦截表达式包装在一起。
 * @author: 張青云
 * @create: 2021-08-24 00:51
 **/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切点
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

}
