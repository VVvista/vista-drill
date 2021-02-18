package com.vista.drill.xiaochao.栈队列;

import com.vista.drill.xiaochao.二叉搜索树.TreeNode;

/**
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 *
 * @author Wen TingTing by 2021/2/6
 */
public class 最大二叉树 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return constructTree(nums, 0, nums.length);

    }

    public TreeNode constructTree(int[] nums, int start, int end) {
        if (start == end) return null;
        int maxIndex = findMaxIndex(nums, start, end);
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructTree(nums, start, maxIndex);
        node.right = constructTree(nums, maxIndex + 1, end);
        return node;
    }

    public int findMaxIndex(int[] nums, int start, int end) {
        int max = start;
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        return max;
    }
}
