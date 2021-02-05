package com.vista.drill.xiaochao.二叉搜索树;

/**https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/
 * @author Wen TingTing by 2021/2/4
 */
public class 二叉搜索树中的插入操作 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null) return  new TreeNode(val);
        if(root.val==val) return root;
        if(val<root.val) root.left=insertIntoBST(root.left,val);
        if(val>root.val) root.right=insertIntoBST(root.right,val);
        return root;

    }
}
