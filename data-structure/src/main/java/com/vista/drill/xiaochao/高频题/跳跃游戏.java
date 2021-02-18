package com.vista.drill.xiaochao.高频题;

/**
 * https://leetcode-cn.com/problems/jump-game/
 *
 * @author Wen TingTing by 2021/2/17
 */
public class 跳跃游戏 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n-1; i++) {
            farthest = Math.max(farthest, nums[i] +i);
            if (farthest <= i) return false;
        }
        return farthest <= n - 1;
    }
}
