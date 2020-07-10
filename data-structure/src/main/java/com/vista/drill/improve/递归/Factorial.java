package com.vista.drill.improve.递归;

/**
 * 5.阶乘
 * 求 n 的阶乘 1 * 2 * 3 * … * (n - 1) * n （n>0）
 * 1.一般递归
 * 2.尾调用优化
 *
 * @author WenTingTing by 2020/5/5
 */
public class Factorial {
    /**
     * 递归
     * 求n的阶乘
     * 注：此处不为尾调用
     *
     * @param n
     * @return
     */
    public int factorial(int n) {
        return n * factorial(n - 1);
    }

    /**
     * 尾递归
     * 计算n的阶乘
     *
     * @param n
     * @return
     */
    public int factorial1(int n) {
        return factorial0(n, 1);
    }

    /**
     * 从大到小累乘
     *
     * @param n
     * @param result 从大到小累乘的结果
     * @return
     */
    private int factorial0(int n, int result) {
        if (n <= 1) return result;
        return factorial0(n - 1, result * n);

    }
}
