package com.vista.drill.improve.recursion;

/**
 * 3.上台阶(跳台阶)
 * 楼梯有n阶台阶，每次可以一步上1阶，也可以一步上2阶，问走完n阶台阶共有多少种不同的走法
 *
 * @author WenTingTing by 2020/5/5
 */
public class ClimbStairs {

    /**
     * 递归
     * 走完n阶台阶共有多少种走法
     * <p>
     * T(n) = T(n − 1) + T(n − 2) + O(1) => T(n) = O(2^n)
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)
     * <p>
     * 注：
     * 复杂度与分析、优化都与斐波那契数列相同
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

}
