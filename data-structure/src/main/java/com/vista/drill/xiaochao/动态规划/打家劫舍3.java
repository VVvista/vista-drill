package com.vista.drill.xiaochao.动态规划;

import com.vista.drill.base.map.HashMap;

/**https://leetcode-cn.com/problems/house-robber-iii/
 * @author Wen TingTing by 2021/1/31
 */
public class 打家劫舍3 {
    HashMap<TreeNode,Integer> map=new HashMap<TreeNode,Integer>();
    public int rob(TreeNode root) {
        if(root==null) return 0;
        if(map.containsKey(root)) return map.get(root);
        int sum1=root.val;
        if(root.left!=null){
            sum1+=rob(root.left.left)+rob(root.left.right);
        }
        if(root.right!=null){
            sum1+=rob(root.right.left)+rob(root.right.right);
        }
        int sum2=rob(root.left)+rob(root.right);
        int sum=Math.max(sum1,sum2);
        map.put(root,sum);
        return sum;
    }
}
// Definition for a binary tree node.
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }