package com.vista.drill.raise.h高频题;

import java.util.HashMap;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍
 * <p>
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * @author WenTingTing by 2020/12/17
 */
public class b_两数之和 {
    /**
     * 方法1： 暴力法
     * 双重for循环枚举每一对正数，并判断两数之和与目标值是否相等
     * 缺点： 时间复杂度o(n^2),空间复杂度0(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) return null;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }

            }
        }
        return new int[]{};
    }

    /**
     * 方法2： 一趟扫描,利用hash表
     * 思路：循环遍历元素，并依次放入hash表，去hash表中查找是否存在 target-nums[i] 值
     * hash<nums[i],i>
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null) return null;
        HashMap hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if (hash.containsKey(other)) {
                int index = (int) hash.get(other);
                return new int[]{index, i};
            }
            hash.put(nums[i], i);
        }
        return new int[]{};
    }


}
