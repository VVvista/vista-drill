package com.vista.design.drill.proxy.dynamicproxy;

/**
 * @author WenTingTing by 2020/4/13
 */
public class Station implements Subject {
    @Override
    public void sell() {
        System.out.println("贩卖火车票");
    }
}
