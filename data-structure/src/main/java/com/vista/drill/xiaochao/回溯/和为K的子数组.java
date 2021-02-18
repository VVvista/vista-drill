package com.vista.drill.xiaochao.回溯;

/**https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * @author Wen TingTing by 2021/2/15
 */
public class 和为K的子数组 {
    public int subarraySum(int[] nums, int k) {
        if(nums==null||nums.length==0) return 0;
        int[] presum=new int[nums.length+1];
        for (int i = 1; i < nums.length+1; i++) {
            presum[i]=presum[i-1]+nums[i-1];
        }
        int result=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if(presum[i+1]-presum[j]==k){
                    result++;
                }
            }
        }
        return result;

    }
}
