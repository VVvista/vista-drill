package com.vista.drill.improve.b递归;

/**
 * 1.求1+2+3+...+n的和(n>0)
 * <p>
 * 注：使用递归不是为了求得最优解，而是为了简化解决问题的思路，使代码更加简洁
 * 递归求出来的有可能不是最优解，有可能是最优解
 * 递归一定可以转换为非递归
 *
 * @author WenTingTing by 2020/5/5
 */
public class Sum {

    /**
     * 公式法
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int sum1(int n) {
        if (n <= 1) return n;
        return (1 + n) * n / 2;
    }

    /**
     * 循环法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public int sum2(int n) {
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += n;
        }
        return sum;
    }

    /**
     * 递归法
     * 总消耗时间 T(n) = T(n − 1) + O(1) => T(n) = O(n)
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public int sum3(int n) {
        if (n <= 1) return n;
        return n + sum3(n - 1);
    }

}
