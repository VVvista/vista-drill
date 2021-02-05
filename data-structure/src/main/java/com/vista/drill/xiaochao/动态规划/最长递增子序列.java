package com.vista.drill.xiaochao.动态规划;

/**
 * @author Wen TingTing by 2021/1/30
 */
public class 最长递增子序列 {
    int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
