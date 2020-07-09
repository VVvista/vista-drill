package com.vista.leetcode.贪心;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 *
 * @author WenTingTing by 2020/7/9
 */
public class E_122_买卖股票的最佳时机2 {

    /**
     * 从第 i 天（这里 i >= 1）开始，与第 i - 1 的股价进行比较，如果股价有上升（严格上升），
     * 就将升高的股价（ prices[i] - prices[i- 1] ）记入总利润，按照这种算法，得到的结果就是符合题意的最大利润。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int money = 0;// 获取利润
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                int diff = prices[i] - prices[i - 1];
                money = diff > 0 ? diff + money : money;
            }
        }
        return money;
    }
}
