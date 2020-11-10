package com.vista.drill.raise.c栈队列;

import lombok.val;

import java.util.Stack;

/**
 * 4.最大二叉树扩展
 * 返回一个数组，数组里面存着每个结点的父节点的索引，若没有父节点，返回-1
 * 结点的父节点的特点：左右两边第一个比它大的值的最小值
 * 思路： 创建两个数组 nums1、nums2，
 * - nums1:存储每个结点左边第一个比它大的值
 * - nums2:存储每个结点右边第一个比它大的值
 * nums1、nums2两者中相对较小值，即为该结点的父节点
 * <p>
 * 实现：创建一个栈(存储索引)
 * - 栈：栈底->栈顶 单调递减
 * - 将数组元素单调递减的存入栈中
 * - 如果值a，满足单调递减的条件，则入栈，栈顶元素是该值a的左边第一个比它大的元素
 * - 如果值a，不满足单调递减条件，则弹出栈顶元素，弹出元素右边第一个比它大的元素即为值a；
 *
 * @author WenTingTing by 2020/10/20
 */
public class d_最大二叉树扩展 {
    public int[] constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        int[] nums1 = new int[nums.length];
        int[] nums2 = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums1[i] = nums2[i] = -1;
        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for (int i = 1; i < nums1.length; i++) {
            while (nums[i] > nums[stack.peek()]) {
                nums2[stack.pop()] = i;
            }
            nums1[i] = stack.peek();
            stack.push(i);
        }

        int[] max = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums1[i] == -1 && nums2[i] == -1) {
                max[i] = -1;
                continue;
            }

        }
        return null;
    }

}

