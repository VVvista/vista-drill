package com.vista.drill.xiaochao.动态规划;

/**https://leetcode-cn.com/problems/house-robber/
 * @author Wen TingTing by 2021/1/31
 */
public class 打家劫舍 {
    public int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        int[] dp=new int[nums.length+1];
        dp[nums.length]=0;
        dp[nums.length-1]=nums[nums.length-1];
        for (int i = nums.length-2; i >=0; i--) {
            dp[i]=Math.max(nums[i]+dp[i+2],dp[i+1]);
        }
        return dp[0];
    }
}
