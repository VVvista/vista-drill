package com.vista.leetcode.tree.binarytree;


import java.util.LinkedList;
import java.util.Queue;

/**https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * 效率：maxDepth1>maxDepth2
 * @author Wen TingTing by 2020/4/11
 */
public class E_104_二叉树的最大深度 {
    /**
     * 递归算法
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
    }

    /**
     * 层序遍历求得最大深度
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int depth = 0;
        int size = 1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            size--;
            if(node.left!=null) queue.offer(node.left);
            if(node.right!=null) queue.offer(node.right);
            if(size==0){
                size=queue.size();
                depth++;
            }
        }
        return depth;



    }
}
