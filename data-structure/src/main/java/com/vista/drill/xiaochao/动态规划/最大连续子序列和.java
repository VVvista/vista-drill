package com.vista.drill.xiaochao.动态规划;

/**https://leetcode-cn.com/problems/maximum-subarray/
 * @author Wen TingTing by 2021/1/30
 */
public class 最大连续子序列和 {
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) dp[i] = dp[i - 1] + nums[i];
            else dp[i] = nums[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
