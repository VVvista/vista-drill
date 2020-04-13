package com.vista.java.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类调用方法底层是通过调用invoke()方法实现
 * 解决：当通过代理类对象调用方法a时，如何动态的调用具体类的同名方法a
 * 当使用代理类调用方法a时，底层会调用该类的invoke()方法实现调用具体类中的方法a
 *
 * @author WenTingTing by 2020/4/13
 */
public class MyInvocationHandler implements InvocationHandler {

    /**
     * 抽象类对象
     */
    private Object human;

    public MyInvocationHandler(Object human) {
        this.human = human;
    }

    /**
     * @param proxy  代理类对象
     * @param method 调用方法
     * @param args   方法参数
     * @return 方法返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("方法调用前-处理逻辑");

        final Object returnValue = method.invoke(human, args);

        System.out.println("方法调用后-处理逻辑");

        return returnValue;
    }
}
