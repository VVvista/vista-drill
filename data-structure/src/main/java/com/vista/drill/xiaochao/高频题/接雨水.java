package com.vista.drill.xiaochao.高频题;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *
 * @author Wen TingTing by 2021/2/17
 */
public class 接雨水 {
    /**
     * 方法1：暴力法
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if(height==null||height.length==0) return 0;
        int n = height.length;
        // 计算左侧最大值
        int[] lmax = new int[n];
        lmax[0] = height[0];
        for (int i = 1; i < n; i++) {
            lmax[i] = Math.max(lmax[i - 1], height[i]);
        }
        //计算右侧最大值
        int[] rmax = new int[n];
        rmax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i + 1], height[i]);
        }
        // 计算最后结果
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(lmax[i], rmax[i]) - height[i];
        }
        return res;
    }
    /**
     * 方法2：双指针
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if(height==null||height.length==0) return 0;
        int n=height.length;
        int lmax=height[0];
        int rmax=height[n-1];
        int left=0;
        int right=n-1;
        int res=0;
        while(left<=right){
            lmax=Math.max(height[left],lmax);
            rmax=Math.max(height[right],rmax);
            if(lmax>rmax){
                res+=rmax-height[right];
                right--;
            }else{
                res+=lmax-height[left];
                left++;
            }
        }
        return res;
    }
}
