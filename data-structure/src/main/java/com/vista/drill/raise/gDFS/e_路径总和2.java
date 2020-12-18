package com.vista.drill.raise.gDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 思路： 设置路径总和为n，依次减去节点值，直到（左右节点为null&& n==0）,string加入结果集，否则递归dfs(左右节点)
 * https://leetcode-cn.com/problems/path-sum-ii/
 *
 * @author WenTingTing by 2020/12/16
 */
public class e_路径总和2 {
    List<List<Integer>> result;
    List<Integer> list;
    int sum;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        result = new ArrayList<>();
        list = new ArrayList<>();
        this.sum = sum;
        if (root == null) return result;
        dfs(root);
        return result;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        sum -= node.val;
        list.add(node.val);
        if (node.left == null && node.right == null) {
            if (sum == 0) {
                // 错误写法 result.add(list):仅是将list指针加入result中，后续list发生变化，result中集合数据也发生变化
                result.add(new ArrayList<>(list));
            }
        } else {
            dfs(node.left);
            dfs(node.right);
        }
        sum += node.val;
        list.remove(list.size() - 1);
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }
}

