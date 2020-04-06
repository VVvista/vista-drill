package com.vista.design.drill.singleton;

/**
 * 饿汉式-静态变量
 * 缺点：当未调用getInstance时，可能造成内存浪费
 *
 * @author Wen TingTing by 2020/4/5
 */
public class Singleton01 {
    private final static Singleton01 instance = new Singleton01();

    private Singleton01() {
    }

    public Singleton01 getInstance() {
        return instance;
    }
}
