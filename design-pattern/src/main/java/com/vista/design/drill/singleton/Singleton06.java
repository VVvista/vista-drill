package com.vista.design.drill.singleton;

/**
 * 静态内部类
 * 推荐使用
 *
 * @author Wen TingTing by 2020/4/5
 */
public class Singleton06 {
    private static class Singleton06Instance {
        private static Singleton06 instance = new Singleton06();
    }

    private Singleton06() {
    }

    public Singleton06 getInstance() {
        return Singleton06Instance.instance;
    }
}
