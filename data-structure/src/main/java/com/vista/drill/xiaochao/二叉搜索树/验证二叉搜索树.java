package com.vista.drill.xiaochao.二叉搜索树;

/**https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author Wen TingTing by 2021/2/4
 */
public class 验证二叉搜索树 {
    /**
     * 方法1
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        return isValidBST(root,null,null);
    }
    public boolean isValidBST(TreeNode root,TreeNode min,TreeNode max) {
        if(root==null) return true;
        if(min!=null&& min.val>=root.val) return false;
        if(max!=null && max.val<=root.val) return false;
        return  isValidBST(root.left,min,root)&& isValidBST(root.right,root,max);

    }

    /**
     * 方法2
     * 中序遍历，验证其是否单调递增
     * @param root
     * @return
     */
    TreeNode pre;
    boolean isValidBST =true;
    public boolean isValidBST1(TreeNode root) {
        if(root==null) return true;
        inorder(root);
        return  isValidBST;
    }

    public void inorder (TreeNode root) {
        if(root==null) return;
        inorder(root.left);
        if(pre!=null&& pre.val>=root.val)
        {
            isValidBST=false;
        }
        pre=root;
        inorder(root.right);
    }

}
