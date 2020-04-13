package com.vista.java.base.proxy.subject;

/**
 * @author WenTingTing by 2020/4/13
 */
public class Station implements Subject{
    @Override
    public void sell() {
        System.out.println("网上订票app");
    }
}
