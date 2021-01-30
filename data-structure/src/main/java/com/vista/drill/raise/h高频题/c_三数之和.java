package com.vista.drill.raise.h高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * @author WenTingTing by 2020/12/17
 */
public class c_三数之和 {


    /**
     * 思路：
     * 1.先将数组元素进行排序(升序)
     * 2.循环遍历数组元素i
     * - 设置两个指针 l、r
     * -- l: 初始 i+1
     * -- r: 初始 nums.length-1
     * 3.
     * - 若nums[i]+nums[l]+nums[r]<0, l++
     * - 若nums[i]+nums[l]+nums[r]>0, r++
     * - 若nums[i]+nums[l]+nums[r]=0, l++,r++
     * -- 为了避免包含重复三元组，此时l++ ,r++ ,直至nums[l-1]!=nums[l],nums[r-1]!=nums[r]
     * 直至l==r,i进行下一轮循环 i++
     * 同时为了避免包含重复三元组，nums[i-1]==nums[i]时，i++
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) return null;
        // 最终所有组合的集合
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length < 3) return result;
        // 数组排序
        Arrays.sort(nums);
        // 循环遍历数组元素
        for (int i = 0; i < length - 2; i++) {
            // 跳过nums[i-1]==nums[i]的情况
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            // 设置指针l、r
            int l = i + 1;
            int r = length - 1;
            // 循环遍历l、r
            while (l < r) {

                if (nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                } else if (nums[i] + nums[l] + nums[r] < 0) {
                    l++;
                } else {
                    // nums[i] + nums[l] + nums[r] =0
                    // 将三元组添加进结果集
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 跳过相同的值（去重）
                    // 退出循环时，nums[l]为相同数字的最后一个，所以还需l++，获取到第一个不相同的数字
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }

                    // 往中间逼近
                    l++;
                    r--;
                }
            }
        }
        return result;


    }
}
