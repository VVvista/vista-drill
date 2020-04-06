package com.vista.design.drill.singleton;

/**
 * 饿汉式-静态代码块
 * 与饿汉式-方式1没有太多区别
 *
 * @author Wen TingTing by 2020/4/5
 */
public class Singleton02 {
    private static Singleton02 instance;

    static {
        instance = new Singleton02();
    }

    private Singleton02() {
    }

    public Singleton02 getInstance() {
        return instance;
    }
}
