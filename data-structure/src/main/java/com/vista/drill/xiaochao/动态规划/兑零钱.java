package com.vista.drill.xiaochao.动态规划;

/**
 * @author Wen TingTing by 2021/1/30
 */
public class 兑零钱 {
    /**
     * 1.暴力法
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {

        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int nums = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int sub = coinChange(coins, amount - coins[i]);
            if (sub == -1) continue;
            nums = Math.min(nums, sub + 1);
        }
        return nums == Integer.MAX_VALUE ? -1 : nums;
    }

    /**
     * 2.带备忘录的递归
     *
     * @param coins
     * @param amount
     * @return
     */
    int[] dp;

    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
         dp = new int[amount + 1];
        return coinChange2(coins, amount);
    }

    public int coinChange2(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        if (dp[amount] == 0) {
            int nums = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                int sub = coinChange2(coins, amount - coins[i]);
                if (sub == -1) continue;
                nums = Math.min(nums, sub + 1);
            }
            dp[amount] = nums == Integer.MAX_VALUE ? -1 : nums;
        }
        return dp[amount];
    }

    /**
     * 动态规划
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange3(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int nums = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int sub = coinChange(coins, amount - coins[i]);
            if (sub == -1) continue;
            nums = Math.min(nums, sub + 1);
        }
        return nums == Integer.MAX_VALUE ? -1 : nums;
    }
}
