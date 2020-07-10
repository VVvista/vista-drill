package com.vista.leetcode.分治;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * https://leetcode-cn.com/problems/majority-element
 *
 * @author WenTingTing by 2020/7/9
 */
public class E_169_多数元素 {
    public static void main(String[] args) {
        majorityElement(new int[]{3, 2, 3});
    }

    /**
     * 找出数组多数元素
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        return majorityElement(nums, 0, nums.length);
    }

    /**
     * 找出指定范围内的多数元素
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;
        int leftEle = majorityElement(nums, begin, mid);
        int rightEle = majorityElement(nums, mid, end);
        if (leftEle == rightEle) return leftEle;
        int leftCount = 0;
        int rightCount = 0;
        for (int i = begin; i < end; i++) {
            if (nums[i] == leftEle) leftCount++;
            if (nums[i] == rightEle) rightCount++;
        }
        return leftCount > rightCount ? leftEle : rightEle;
    }

}
