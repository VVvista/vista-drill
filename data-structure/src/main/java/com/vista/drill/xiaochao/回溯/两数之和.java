package com.vista.drill.xiaochao.回溯;

import com.vista.drill.base.map.HashMap;
import com.vista.drill.base.map.Map;

/**
 * @author Wen TingTing by 2021/2/13
 */
public class 两数之和 {
    /**
     * 穷举法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if(nums==null||nums.length==0) return null;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};

    }
    /**
     * hash法
     * @param nums
     * @param target
     * @return
     */
    Map<Integer,Integer> map;
    public int[] twoSum1(int[] nums, int target) {
        if(nums==null||nums.length==0) return null;
        map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int other=target-nums[i];
            if(map.containsKey(other)&& map.get(other)!=i){
                return new int[]{i,map.get(other)};
            }
        }
        return new int[]{-1,-1};

    }
}
