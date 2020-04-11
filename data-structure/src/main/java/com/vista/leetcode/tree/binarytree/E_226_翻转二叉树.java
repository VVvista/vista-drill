package com.vista.leetcode.tree.binarytree;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * @author Wen TingTing by 2020/4/11
 */
public class E_226_翻转二叉树 {

    /**
     * 递归算法
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.right = leftNode;
        root.left = rightNode;
        return root;
    }
}
