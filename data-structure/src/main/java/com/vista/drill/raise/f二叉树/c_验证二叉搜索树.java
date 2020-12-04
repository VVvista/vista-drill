package com.vista.drill.raise.f二叉树;

/**
 * 1. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 *
 * @author WenTingTing by 2020/12/3
 */
public class c_验证二叉搜索树 {
    TreeNode pre;
    boolean isValidBST = true;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        inorder(root);
        return isValidBST;
    }

    private void inorder(TreeNode node) {
        // 初始条件
        if (node == null) return;

        // 状态转移方程
        inorder(node.left);
        // 找到错误节点
        if (pre != null && pre.val >= node.val) {
            isValidBST = false;
        }
        pre = node;
        inorder(node.right);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
