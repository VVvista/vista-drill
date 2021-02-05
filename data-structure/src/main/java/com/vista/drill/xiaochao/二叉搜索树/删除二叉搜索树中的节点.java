package com.vista.drill.xiaochao.二叉搜索树;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 *
 * @author Wen TingTing by 2021/2/4
 */
public class 删除二叉搜索树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            if (root.left == null && root.right == null) return null;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            TreeNode node = search(root.right);
            root.val = node.val;
            root.right = deleteNode(root.right, node.val);
        }
        if (key < root.val) root.left = deleteNode(root.left, key);
        else root.right = deleteNode(root.right, key);
        return root;

    }

    public TreeNode search(TreeNode node) {
        if (node == null) return null;
        while (node.left != null) node = node.left;
        return node;
    }
}
