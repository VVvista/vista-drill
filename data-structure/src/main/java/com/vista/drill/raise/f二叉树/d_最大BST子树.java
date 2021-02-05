package com.vista.drill.raise.f二叉树;

/**
 * 4.最大BST子树
 * 给定一个二叉树，找到其中最大的二叉搜索树，其中最大指的是子树结点数最多的
 * 注意：子树必须包含其所有的后代
 *
 * @author WenTingTing by 2020/12/4
 */
public class d_最大BST子树 {
    /**
     * 方法1： 自顶向下(递归)
     * 以root为根节点的二叉树的最大BST子树的结点数量
     * - 若以root为根节点的二叉树是BST，返回root二叉树的结点数量
     * - 否则，max(func(root.left,root.right))
     *
     * @param root
     * @return
     */
    public int largestBSTSubtree(TreeNode root) {
        return 0;
    }
    /**
     * 方法2： 自底向上
     *
     * @param node
     * @return
     */
    private int largestBSTTree(TreeNode node) {
        return getInfo(node).size;
    }

    /**
     * 以node为根节点的二叉树的最大BST子树的信息
     * 思路： 以root为根结点的二叉树是一棵BST，最大的BST子树就是其本身。
     * 共四种情况
     * - li != null && ri != null
     * && li.node == root.left && li.max < root.val
     * && ri.node == root.right && ri.min > root.val
     * - li != null && ri == null
     * && li.node == root.left && li.max < root.val
     * - li == null && ri != null
     * && ri.node == root.right && ri.min > root.val
     * - li == null && ri == null
     *
     * @param root
     * @return
     */
    private Info getInfo(TreeNode root) {
        if (root == null) return null;
        Info li = getInfo(root.left);
        Info ri = getInfo(root.right);
        // 以root为根节点的二叉树是BST
        int leftBstSize = -1;
        int rightBstSize = -1;
        int max = root.val;
        int min = root.val;

        if (li == null) {
            leftBstSize = 0;
        } else if (li.node == root.left && li.max < root.val) {
            leftBstSize = li.size;
            min = li.min;
        }

        if (ri == null) {
            rightBstSize = 0;
        } else if (ri.node == root.right && ri.min > root.val) {
            rightBstSize = ri.size;
            max = ri.max;
        }

        if (leftBstSize >= 0 && rightBstSize >= 0) {
            return new Info(root, 1 + leftBstSize + rightBstSize, max, min);
        }

        // 以root为根节点的二叉树并不是BST

        // 左右子树BST均不为null，返回结点总数多的
        if (li != null && ri != null) {
            return li.size > ri.size ? li : ri;
        }

        // 左右子树BST有一个为null，返回不为null的info
        return li != null ? li : ri;

    }

    /**
     * 最大BST子树的信息
     */
    private static class Info {
        // 根节点
        TreeNode node;
        // 结点总数
        int size = 1;
        // 最大值
        int max;
        // 最小值
        int min;

        public Info(TreeNode node, int size, int max, int min) {
            this.node = node;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }
}
