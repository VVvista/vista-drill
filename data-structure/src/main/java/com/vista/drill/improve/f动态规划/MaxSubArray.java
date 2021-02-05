package com.vista.drill.improve.f动态规划;

/**
 * 1.最大连续子序列和
 * 给定一个长度为 n 的整数序列，求它的最大连续子序列和
 * 状态：dp(i) 以 nums(i)结尾的最大连续子序列和
 * 初始状态：dp(0)=nums(0)
 * 状态转移方程：
 * 若dp(i-1)>0,dp(i)=dp(i-1)+nums(i)
 * 若dp(i-1)<=0,dp(i)=nums(i)
 *https://leetcode-cn.com/problems/maximum-subarray/
 * @author WenTingTing by 2020/9/22
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray1(nums));
        System.out.println(maxSubArray2(nums));
    }

    /**
     * 动态规划
     * 方案1
     * * 状态转移方程：
     * * 若dp(i-1)>0,dp(i)=dp(i-1)+nums(i)
     * * 若dp(i-1)<=0,dp(i)=nums(i)
     * *
     *
     * @param nums
     * @return
     */
    private static int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        int max = nums[0];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 动态规划
     * 方案2：方案1优化
     * 1.在方案1基础上将中间状态由数组int[]保存改为变量int 存储
     * 2.优化状态转移方程:
     * dp(i)=max{dp(i-1)+nums(i),nums(i)}
     *
     * @param nums
     * @return
     */
    private static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int dp = nums[0];// 中间状态由变量保存
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(dp + nums[i], nums[i]);
            max = Math.max(dp, max);
        }
        return max;
    }

}
