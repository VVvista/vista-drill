package com.vista.drill.improve.f动态规划;

/**
 * 2.最长上升子序列长度
 * 给定一个无序的整数序列，求出它最长上升子序列的长度（要求严格上升，子序列不连续）
 * 状态：dp(i) 以 nums(i)结尾的最长上升子序列和长度
 * 初始状态：dp(i)=1
 * 状态转移方程：
 * 循环j [0,i)，
 * 若 nums[j]<nums[i],dp=dp[j]+1， 最大的dp 即为dp(i)
 * 若 nums[j]>=nums[i] ,dp[i]=1 即初始值
 *
 * @author WenTingTing by 2020/9/22
 */
public class LIS {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(lengthOfLIS1(nums));
        System.out.println(lengthOfLIS2(nums));
    }

    /**
     * 方案1：
     * 动态规划
     *
     * @param nums
     * @return
     */
    static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 方案2：
     * 二分搜索法实现
     *
     * @param nums
     * @return
     */
    static int lengthOfLIS2(int[] nums) {





        return 0;
    }
}
