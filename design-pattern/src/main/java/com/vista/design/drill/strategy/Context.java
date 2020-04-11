package com.vista.design.drill.strategy;

/**
 * 环境类
 * 添加抽象策略类的应用，对外提供调用策略类方法的方法
 *
 * @author Wen TingTing by 2020/4/11
 */
public class Context {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int calculate(int a, int b) {
        return strategy.calc(a, b);
    }


}
