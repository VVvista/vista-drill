package com.vista.design.drill.singleton;

/**
 * 懒汉式-线程不安全
 *
 * @author Wen TingTing by 2020/4/5
 */
public class Singleton03 {
    private static Singleton03 instance;

    private Singleton03() {
    }

    public Singleton03 getInstance() {
        if (instance == null) {
            instance = new Singleton03();
        }
        return instance;
    }
}
