package com.vista.drill.raise.d串;

import com.vista.drill.raise.c栈队列.TreeNode;

/**
 * 2.另一个树的子树
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * <p>
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 *
 * @author Wen TingTing by 2020/11/12
 */
public class b_另一个树的子树 {
    /**
     * 思路：将树s、t序列化输出，判断t串是否为s的子串
     * - 前序/后序遍历s、t，将其序列化为字符串
     * - 判断t串是否为s的子串
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        StringBuffer sb = new StringBuffer();
        StringBuffer st = new StringBuffer();
        postSerialize(s, sb);
        postSerialize(t, st);
        return sb.toString().contains(st.toString());
    }

    /**
     * 后序遍历输出树节点
     * * 注意：
     * * 1.序列化后为了区分 '结点12' 与 '结点1、结点2',每个结点后添加特殊字符'!'
     * * 2.序列化后为了能唯一确定一棵树，结点的空子节点使用'#!'代替，并序列化打印
     * * 3.若使用前序遍历，序列化串头部加上'!'，避免 '12!#!#!' 与 '2!#!#!' 的特殊情况
     *
     * @param node
     * @param s
     */
    private void postSerialize(TreeNode node, StringBuffer s) {
        if (node == null) {
            s.append("#!");
            return;
        }
        postSerialize(node.left, s);
        postSerialize(node.right, s);
        s.append(node.val).append("!");
    }
}
