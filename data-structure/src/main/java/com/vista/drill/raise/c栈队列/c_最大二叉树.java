package com.vista.drill.raise.c栈队列;

/**
 * 3.最大二叉树
 * 给定一个不含重复元素的整数数组，将数组构建成一个最大二叉树
 * 根：数组中最大元素
 * 左子树：数组最大值左边部分构造出的最大二叉树
 * 右子树：数组最大值右边部分构造出的最大二叉树
 * 通过给定的数组构建最大二叉树，并输出这个树的根结点
 * <p>
 * 思路：递归实现
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 *
 * @author WenTingTing by 2020/10/20
 */
public class c_最大二叉树 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) return null;
        return findRoot(nums, 0, nums.length);
    }

    /**
     * 查找数组指定范围内的最大二叉树的根结点
     * [l,r)
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private TreeNode findRoot(int[] nums, int l, int r) {
        if (l == r) return null;
        // 查找最大元素的索引
        int maxIndex = findMaxIndex(nums, l, r);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = findRoot(nums, l, maxIndex);
        root.right = findRoot(nums, maxIndex + 1, r);
        return root;
    }

    /**
     * 查找数组指定范围内最大元素的索引
     * [l,r)
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int findMaxIndex(int[] nums, int l, int r) {
        int max = l;
        for (int i = l+1; i < r; i++) {
            max = nums[i] > nums[max] ? i : max;
        }
        return max;
    }
}