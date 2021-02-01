package com.vista.drill.xiaochao.动态规划;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author Wen TingTing by 2021/1/31
 */
public class 买卖股票的最佳时机 {
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];

        dp[0] = 0;
        int max = 0;
        int minIndex = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[minIndex]) {
                dp[i] = prices[i] - prices[minIndex];
                if (dp[i] > max) {
                    max = dp[i];
                }
            } else {
                dp[i] = 0;
                minIndex = i;
            }
        }
        return max;
    }

}
