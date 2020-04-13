package com.vista.design.drill.proxy.staticproxy;

/**
 * 具体类
 * 实现抽象类，并实现抽象接口的具体处理逻辑
 * 代理类的代理的实际对象
 *
 * @author WenTingTing by 2020/4/13
 */
public class Station implements Subject {

    @Override
    public void sell() {
        System.out.println("卖火车票");
    }
}
