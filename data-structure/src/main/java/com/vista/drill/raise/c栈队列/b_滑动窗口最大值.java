package com.vista.drill.raise.c栈队列;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 2.滑动串口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3 -1] -3  5  3  6  7      3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @author WenTingTing by 2020/10/20
 */
public class b_滑动窗口最大值 {
    /**
     * 方法1： 双端队列
     * - 双端队列：存放索引。队列元素从头到尾对应的元素值递减
     * 思路：设置2个指针
     * - w:窗口头部（注意有效值）
     * - i:遍历元素，即滑动窗口最后一个元素指针[0,nums.lenth)-> 注意有效值
     * - 如果nums[i]>=队尾，不断删除队尾，直到 队尾>nums[i] 为止，nums[i]入队
     * - 检查队头是否过期(有效)，过期则移除，设置w窗口最大值为nums[队头]
     * 注：若每次只移动一位，则队列中过期的队头，只可能有一个
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return null;
        if (k == 1) return nums;
        // 存储最大值的数组
        int[] maxes = new int[nums.length - k + 1];
        // 双端队列
        Deque<Integer> deque = new LinkedList<>();

        // 遍历数组元素
        for (int i = 0; i < nums.length; i++) {
            int w = i - k + 1;
            // 数组元素与队尾元素比较
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            // 数组索引入队
            deque.offerLast(i);
            if (w >= 0) {
                // 验证队头索引是否有效,移动步长为1时，只可能删除队头一个元素
                if (deque.peekFirst() < w) {
                    // 若无效删除队头元素
                    deque.pollFirst();
                }
                // 获取队头元素
                maxes[w] = nums[deque.peekFirst()];
            }
        }

        // 返回最大数组
        return maxes;
    }
}
