package com.vista.drill.raise.h高频题;

/**
 * Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * @author WenTingTing by 2020/12/21
 */
public class d_pow {
    /**
     * 方法： 快速幂（分治）
     * 思路：
     * 将n分成两份，利用 x^n=x^(n/2) * x^(n/2) 递归求出结果
     * 注意：
     * - n为奇数(正负均是)时： x^n=x^(n/2) * x^(n/2) * n
     * -- x^(-7)=x^(-4) * x^(-4) * x
     * - n为偶数(正负均是)时： x^n=x^(n/2) * x^(n/2)
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        // 初始条件
        if (n == -1) {
            return 1 / x;
        }
        if (n == 0) {
            return 1;
        }
        // 求n的一半
        int part = n / 2;
        // 判断是否为偶数
        boolean remainder = n % 2 == 0;
        // 递归 分治求一半的结果
        double result = myPow(x, part);
        // 返回结果
        return remainder ? result * result : result * result * x;
    }
}
