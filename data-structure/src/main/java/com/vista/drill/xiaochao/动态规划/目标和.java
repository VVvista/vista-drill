package com.vista.drill.xiaochao.动态规划;

/**
 * https://leetcode-cn.com/problems/target-sum/
 *
 * @author WenTingTing by 2021/2/1
 */
public class 目标和 {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((S + sum) % 2 != 0) return 0;
        int target = (sum + S) / 2;

        int[][] dp = new int[nums.length + 1][target + 1];
        for (int i = 0; i < nums.length + 1; i++) {
            dp[i][0] = 1;
        }
            for (int i = 1; i < nums.length + 1;i++) {
                for (int j = 1; j < target + 1; j++) {
                    if ((j - nums[i - 1]) < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]] ;
                }
            }
        }
        return dp[nums.length][target];
    }
    public int findTargetSumWays2(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((S + sum) % 2 != 0) return 0;
        int target = (sum + S) / 2;

        int[] dp = new int[target + 1];
        dp[0]= 1;

        for (int i = 1; i < nums.length + 1;i++) {
            for (int j = target ; j >= 1; j--) {
                if ((j - nums[i - 1]) < 0) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = dp[j] + dp[j - nums[i - 1]] ;
                }
            }
        }
        return dp[target];
    }
}
