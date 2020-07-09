package com.vista.leetcode.贪心;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * https://leetcode-cn.com/problems/path-sum/
 *
 * @author WenTingTing by 2020/7/7
 */
public class E_112_路径总和 {
    int res = 0;// 路径总和

    public boolean hasPathSum(TreeNode root, int sum) {
        return hasSum(root, sum);
    }

    /**递归算法
     * @param node
     * @param sum
     * @return
     */
    public boolean hasSum(TreeNode node, int sum) {
        if (node == null) return false;
        // 到达叶子节点时，递归终止，判断 sum 是否符合条件。
        if (node.left == null && node.right == null) {
            return sum == node.val;
        }
        // 递归地判断root节点的左孩子和右孩子。

        return hasSum(node.left, sum - node.val) ||
                hasSum(node.right, sum - node.val);
    }
}

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}