package com.vista.drill.raise.f二叉树;

/**
 * 2.恢复二叉搜索树
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用常数空间的解决方案吗？
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 *
 * @author WenTingTing by 2020/12/3
 */
public class b_恢复二叉搜索树 {
    TreeNode first;
    TreeNode second;
    // 前一个结点
    TreeNode pre;

    /**
     * 方法1： 递归
     * 利用二叉搜索树的中序遍历为升序
     * 二叉搜索树中有两个节点被交换，则中序遍历中一定存在两个逆序对(相邻两个数为降序)
     * - 第一个错误节点：第一个逆序对中的较大节点
     * - 第二个错误节点：第二个逆序对中的较小节点
     * 找到该两个节点，交换其值
     * * 时间/空间 复杂度：o(n)
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        inorder(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }


    /**
     * 中序遍历： 找到两个错误节点
     *
     * @param node
     */
    private void inorder(TreeNode node) {
        // 初始条件
        if (node == null) return;

        // 状态转移方程
        inorder(node.left);
        // 找到错误节点
        if (pre != null && pre.val > node.val) {
            if (first == null) {
                first = pre;
            }
            second = node;

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


