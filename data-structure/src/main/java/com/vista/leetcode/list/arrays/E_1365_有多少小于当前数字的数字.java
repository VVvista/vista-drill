package com.vista.leetcode.list.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 * j != i 且 nums[j] < nums[i]
 * <p>
 * 示例：
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * <p>
 * <p>
 * 总结：
 * 解题效率：暴力<hashMap<计数排序
 *
 * @author WenTingTing by 2020/3/30
 */
public class E_1365_有多少小于当前数字的数字 {

    /**
     * 暴力求解
     * <p>
     * 时间复杂度 O(n^2)，空间复杂度 O(n)
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }
                if (nums[j] < nums[i]) {
                    res[i]++;
                }

            }
        }
        return res;
    }


    /**
     * hashMap+排序
     * 解题思路：
     * 1.首先将数组元素及其索引下标存储在hashMap中
     * 2.将数组从小到大排序得到新数组sort
     * 3.从尾到头遍历新数组sort，新数组索引下标便是小于元素的个数。
     * 4.从hashMap中取出元素下标，并在res的下标位置存储元素个数。
     * <p>
     * 时间复杂度 O(nlog(n))，空间复杂度 O(n)
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int length = nums.length;
        // 将数组元素、索引存储在hashMap中
        HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<Integer, HashSet<Integer>>(nums.length);
        for (int i = 0; i < length; i++) {
            if (!hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], new HashSet<Integer>());
            }
            hashMap.get(nums[i]).add(i);
        }

        //对数组从小到大排序
        int[] sort = Arrays.copyOf(nums, length);
        Arrays.sort(sort);
        int[] res = new int[length];
        // 从尾到头循环遍历新数组
        for (int i = length - 1; i >= 0; i--) {
            // 获取元素在原数组中的索引下标
            HashSet<Integer> indexSet = hashMap.get(sort[i]);
            for (int index : indexSet) {
                // 循环索引下标，并将新数组下标值存储在res中
                res[index] = i;
            }
        }
        // 返回res
        return res;
    }

    /**
     * 计数排序
     * 此思路主要针对题目中的要求总结：
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     * <p>
     * 解题思路：
     * 1.创建长度为101的新数组，数组索引代表0-100数值
     * 2.新数组记录每个元素出现的次数
     * 3.所有小于i的数组元素和即为小于该元素的个数
     *
     * 时间复杂度 O(n)，空间复杂度 O(1)
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent3(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        // 创建num[101]
        int[] num = new int[101];
        // 记录nums数组出现的次数
        for (int n : nums) {
            num[n]++;
        }

        // 计算num中小于该元素的个数
        for (int i = 1; i < num.length; i++) {
            num[i] = num[i - 1] + num[i];
        }

        // 获取nums中的元素统计值，存在res中
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                res[i] = num[nums[i] - 1];
            }

        }

        // 返回res
        return res;
    }
}
