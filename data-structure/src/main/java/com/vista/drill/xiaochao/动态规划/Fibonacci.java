package com.vista.drill.xiaochao.动态规划;

/**
 * @author WenTingTing by 2021/1/26
 */
public class Fibonacci {
    /**
     * 暴力法
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 自顶向下
     *
     * @param n
     * @return
     */
    public int fib1(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        return fibb(dp, n);
    }

    public int fibb(int[] dp, int n) {
        if (dp[n] == 0) {
            dp[n] = fibb(dp, n - 1) + fibb(dp, n - 2);
        }
        return dp[n];
    }

    /**
     * 自底向上
     *
     * @param n
     * @return
     */
    public int fib2(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 自底向上-优化
     *
     * @param n
     * @return
     */
    public int fib3(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;
        int[] dp = new int[2];
        dp[0] = dp[1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i % 2] = dp[0] + dp[1];
        }
        return dp[n % 2];
    }

}
