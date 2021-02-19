package com.vista.drill.xiaochao.高频题;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/jump-game-ii/
 *
 * @author Wen TingTing by 2021/2/17
 */
public class 跳跃游戏2 {
    int[] dp;
    int[] nums;
    int n;

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        n = nums.length;
        dp = new int[n];
        this.nums = nums;
        Arrays.fill(this.dp, Integer.MAX_VALUE);
        return jump(0);
    }

    public int jump(int index) {
        if (index >= n - 1) return 0;
        if (dp[index] != Integer.MAX_VALUE) return dp[index];
        for (int i = 1; i <= nums[index]; i++) {
            int sub = jump(index + i);
            dp[index] = Math.min(dp[index], sub + 1);
        }
        return dp[index];
    }

    /**
     * 贪心算法
     * @param nums
     * @return
     */
    public int jump1(int[] nums) {
        int end=0;
        int farthest=0;
        int count=0;
        for (int i = 0; i < nums.length-1; i++) {
            farthest=Math.max(nums[i]+i,farthest);
            if(end==i){
                count++;
                end=farthest;
            }
        }
        return count;
    }
}
