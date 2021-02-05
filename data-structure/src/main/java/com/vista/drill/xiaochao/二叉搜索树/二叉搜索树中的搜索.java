package com.vista.drill.xiaochao.二叉搜索树;

/**
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 *
 * @author Wen TingTing by 2021/2/4
 */
public class 二叉搜索树中的搜索 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (val == root.val) return root;
        if (val < root.val) return searchBST(root.left, val);
        return searchBST(root.right, val);
    }
}
