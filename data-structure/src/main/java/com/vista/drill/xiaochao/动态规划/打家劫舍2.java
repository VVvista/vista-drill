package com.vista.drill.xiaochao.动态规划;

/**
 * https://leetcode-cn.com/problems/house-robber-ii/
 *
 * @author Wen TingTing by 2021/1/31
 */
public class 打家劫舍2 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        return Math.max(rob(nums, 0, len - 2), rob(nums, 1, len - 1));
    }

    public int rob(int[] nums, int start, int end) {
        int[] dp = new int[end + 2];
        dp[end] = nums[end];
        dp[end + 1] = 0;
        for (int i = end - 1; i >= start; i--) {
            dp[i] = Math.max(nums[i] + dp[i + 2], dp[i + 1]);
        }
        return dp[start];
    }
}
