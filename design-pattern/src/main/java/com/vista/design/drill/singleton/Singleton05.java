package com.vista.design.drill.singleton;

/**
 * 懒汉式-双重检查
 * 线程安全
 * 在保证线程安全的前提下，提高效率
 * <p>
 * 推荐使用
 *
 * @author Wen TingTing by 2020/4/5
 */
public class Singleton05 {
    private static Singleton05 instance;

    private Singleton05() {
    }

    public Singleton05 getInstance() {
        if (instance == null) {
            synchronized (Singleton05.class) {
                if (instance == null)
                    instance = new Singleton05();

            }
        }
        return instance;
    }
}