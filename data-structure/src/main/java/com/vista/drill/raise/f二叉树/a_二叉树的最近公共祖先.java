package com.vista.drill.raise.f二叉树;

import java.util.TreeMap;

/**
 * 1.二叉树的最近公共祖先
 * 给定一个二叉树，找到该树中两个指定节点的最近公共祖先
 * <p>
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * @author WenTingTing by 2020/12/3
 */
public class a_二叉树的最近公共祖先 {
    /**
     * 以root为根结点，查找p、q节点的最近公共祖先
     * 分情况讨论：
     * - p、q 分别在root的左右子树中，则最近公共祖先为root
     * - p、q 在root的左右子树的同一侧(最近公共祖先即再改侧)，则另一侧最近公共祖先为null
     * - p、q 不在root的左右子树中，则返回null
     * 方法： 递归
     * - 若p、q在二叉树中，返回两者的最近公共祖先
     * - 若p、q在二叉树中，返回null
     * - 若p、q有一个在二叉树中，返回p或q
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //初始化
        if (root == null) return null;
        if (root == q || root == p) return root;

        // 去左子树中找两者的最近公共祖先
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        // 去右子树中找两者的最近公共祖先
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode != null && rightNode != null) return root;
        return leftNode != null ? leftNode : rightNode;

    }
}
