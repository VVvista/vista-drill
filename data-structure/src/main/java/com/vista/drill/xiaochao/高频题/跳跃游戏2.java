package com.vista.drill.xiaochao.高频题;

/**
 * https://leetcode-cn.com/problems/jump-game-ii/
 *
 * @author Wen TingTing by 2021/2/17
 */
public class 跳跃游戏2 {
    int[] dp;
    int[] nums;
    int n;

    public int jump(int[] nums) {
        n=nums.length;
        dp = new int[n];
        this.nums = nums;
        return jump(0);
    }

    public int jump(int p) {
        if(p>=n-1) return 0;
        if(dp[p]!=0) return dp[p];
        dp[p]=n;
        for (int i = 1; i < nums[p]; i++) {
            int sub=jump(p+i);
            dp[p]=Math.min(dp[p],sub+1);

        }
        return dp[p];
    }
}
