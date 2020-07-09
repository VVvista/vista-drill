package com.vista.leetcode.递归;

/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * https://leetcode-cn.com/problems/longest-univalue-path/
 *
 * @author WenTingTing by 2020/7/7
 */
public class E_687_最长同值路径 {
    int res = 0;

    /**
     * 计算二叉树对应的最长路径
     * [5,5,1,5,5,2,3,5,1,5,1,4,5,6,7]
     *
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        arrowLength(root);
        return res;
    }

    /**
     * 结点对应的最长路径
     * 递归（DFS）
     * 递归函数是求一个子树可以向父节点提供的路径长度
     * 对于当前节点，左子树能提供的长度为 left，如果当前节点值等于左子节点的值，则左链的长度等于left+1，否则为0
     * 对于当前节点，右子树能提供的长度为right，如果当前节点值等于右子节点的值，则右链的长度等于right+1，否则为0
     * 当前子树对父节点提供的最大长度为左右链中较大的一个
     * 当前子树的左右链之和，去和全局最大值比较，试图更新它
     * 参考：https://leetcode-cn.com/problems/longest-univalue-path/solution/zui-chang-tong-zhi-lu-jing-by-hyj8/
     *
     * @param node
     * @return
     */
    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        TreeNode right = node.right;
        TreeNode left = node.left;
        int arrowLeft = arrowLength(left);// 左子树最大长度
        int arrowRight = arrowLength(right);// 右子树最大长度
        int leftLen = 0;
        int rightLen = 0;
        if (left != null && left.val == node.val) {
            leftLen = arrowLeft + 1;// 当前结点与左子节点相同，左子树最大长度+1
        }
        if (right != null && right.val == node.val) {
            rightLen = arrowRight + 1;// 当前结点与右子节点相同，右子树最大长度+1
        }
        res = Math.max(res, leftLen + rightLen);// 当前结点的最大长度：左子长度+右子长度

        return Math.max(leftLen, rightLen);// 当前结点提供给父节点的最大长度： 左子长度、右子长度的最大值
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