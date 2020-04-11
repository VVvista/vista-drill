package com.vista.leetcode.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/
 *
 * @author Wen TingTing by 2020/4/11
 */
public class M_958_二叉树的完全性检验 {
    public boolean isCompleteTree(TreeNode root) {
        //判断后序结点是否为叶子结点
        boolean isComplete = false;
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            if (isComplete && !isLeaf(node)) return false;
            if (hasTwoChildren(node)) {
                nodes.offer(node.left);
                nodes.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                isComplete = true;
                if (node.left != null) {
                    nodes.offer(node.left);
                }
            }
        }
        return true;
    }

    public boolean isComplete(TreeNode root) {
        if (root == null) return false;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        boolean leaf = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (leaf && !isLeaf(node)) return false;
            if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else { // 后面遍历的节点都必须是叶子节点
                leaf = true;
                if (node.left != null){
                    queue.offer(node.left);
                }
            }
        }
        return true;
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;

    }

    public Boolean hasTwoChildren(TreeNode node) {
        return node.left != null && node.right != null;
    }

}
