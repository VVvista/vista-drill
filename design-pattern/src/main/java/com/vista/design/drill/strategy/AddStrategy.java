package com.vista.design.drill.strategy;

/**
 * 具体策略类
 *
 * @author Wen TingTing by 2020/4/11
 */
public class AddStrategy implements Strategy {
    /**
     * 具体的策略算法实现逻辑
     */
    public int calc(int a, int b) {
        return a + b;
    }
}
