package com.vista.drill.raise.e动态规划;

/**
 * 3.买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 *
 * @author Wen TingTing by 2020/11/20
 */
public class c_买卖股票的最佳时机 {
    /**
     * 方法1：动态规划
     * 循环遍历元素，查找在当前卖出获取的最大利润
     * - 循环遍历元素i，获取i之前的最小元素（i与i-1的最小值比较）
     * - 当前最大利润=当前元素-最小元素
     * - Math.max(最大利润)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        // 数组元素大于1
        // 买卖股票获取的最大利润
        int maxPrice = 0;
        // 截止当前元素的最小元素
        int minProfit = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > minProfit) {
                maxPrice = Math.max(maxPrice, prices[i] - minProfit);
            } else {
                minProfit = prices[i];
            }
        }
        return maxPrice;
    }

    /**
     * 方法2：暴力法(双层循环)
     * dp(i,j):第i天买入第j天卖出获取的利润。
     * - dp(i,j)=dp(i,j-1)+prices[j]-prices[j-1]
     * - 最大利润：Math.max(dp(i,j))
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        // 数组元素大于1
        int maxPrices = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            // 只需记录上一个位置(i,j-1)的利润即可
            int preMaxPrices=0;
            for (int j = i + 1; j < prices.length; j++) {
                preMaxPrices=preMaxPrices+prices[j]-prices[j-1];
                maxPrices=Math.max(maxPrices,preMaxPrices);
            }
        }
        return maxPrices;
    }
}
