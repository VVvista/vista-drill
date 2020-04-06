package com.vista.design.drill.singleton;

/**
 * 懒汉式-同步方法
 * 线程安全
 * 缺点：效率比较低
 *
 * @author Wen TingTing by 2020/4/5
 */
public class Singleton04 {
    private static Singleton04 instance;

    private Singleton04() {
    }

    public synchronized Singleton04 getInstance() {
        if (instance == null) {
            instance = new Singleton04();
        }
        return instance;
    }
}