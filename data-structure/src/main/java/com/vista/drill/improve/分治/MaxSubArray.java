package com.vista.drill.improve.分治;

/**
 * 最大连续子序列和
 * 给定一个长度为 n 的整数序列，求它的最大连续子序列和
 * 比如： –2、1、–3、4、–1、2、1、–5、4 的最大连续子序列和是 4 + (–1) + 2 + 1 = 6
 *
 * @author WenTingTing by 2020/7/6
 */
public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray1(nums));
        System.out.println(maxSubArray2(nums));
    }

    /**
     * 解法1：暴力法
     *
     * @param nums
     * @return
     */
    static int maxSubArray1(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Integer.max(sum, max);
            }
        }
        return max;
    }

    /**
     * 解法2：分治
     *
     * @param nums
     * @return
     */
    static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return maxSubArray(nums, 0, nums.length);
    }

    /**
     * 获取[begin,end) 内的最大子序列和
     *
     * @param nums
     * @param begin
     * @param end
     * @return
     */
    static int maxSubArray(int[] nums, int begin, int end) {
        // 递归基: end - begin < 2, 说明只有一个元素, nums[begin] == nums[end]
        if (end - begin < 2) return nums[begin];// begin=end ,仅有一个元素直接返回即可
        int mid = (begin + end) >> 1;

        // 最长子序列是 [i, mid) + [mid, j) 的情况
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {// [i,mid)
            leftMax = Integer.max(leftMax, leftSum += nums[i]);
        }
        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {// [mid, end)
            rightMax = Integer.max(rightMax, rightSum += nums[i]);
        }

        // 最长子序列在 left部分, right部分的情况
        return Math.max(leftMax + rightMax,
                Integer.max(
                        maxSubArray(nums, begin, mid),// 最长子串在[begin, mid)的情况
                        maxSubArray(nums, mid, end)// 最长子串在[mid, end)的情况
                ));
    }
}
