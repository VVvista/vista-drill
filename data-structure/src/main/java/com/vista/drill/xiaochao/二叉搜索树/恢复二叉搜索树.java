package com.vista.drill.xiaochao.二叉搜索树;


/**
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 *
 * @author WenTingTing by 2021/2/5
 */
public class 恢复二叉搜索树 {

    TreeNode pre;
    TreeNode first;
    TreeNode second;

    public void recoverTree(TreeNode root) {
        if (root == null) return;
        inorder(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;

    }


    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (pre != null && pre.val > root.val) {
            if (first == null) {
                first = pre;
            }
            second = root;
        }
        pre = root;
        inorder(root.right);
    }
}
