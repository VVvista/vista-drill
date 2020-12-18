package com.vista.drill.raise.gDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列 - 方法2
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 思路： 仅使用原数组，
 * - 让 0 位置分别与0、1、2、3...n交换
 * - 让 1 位置分别与1、2、3...n交换
 * - 让 2 位置分别与2、3...n交换
 * - ...
 * 注意： 交换完位置，下一层探索完之后，再将两位置恢复成原样，否则
 * https://leetcode-cn.com/problems/permutations/
 *
 * @author WenTingTing by 2020/12/16
 */
public class b_全排列_2 {
    // 所有可能全排列组合
    List<List<Integer>> result;

    // 给定数字
    int[] nums;

    // 全排列深度
    int deepth;

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;
        result = new ArrayList<>();
        this.nums = nums;
        deepth = nums.length;
        if (deepth == 0) return result;
        dfs(0);
        return result;
    }

    /**
     * 探索第idx层全排列组合
     *
     * @param idx
     */
    private void dfs(int idx) {
        // 若idx==全排列深度，将list添加到result中
        if (idx == deepth) {
            List<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < deepth; i++) {
                resultList.add(nums[i]);
            }
            result.add(resultList);
        }

        // 循环遍历所有数字，若数字已经被索引，则跳过，否则将数字加入list中，再向下探索idx+1层
        // 探索完成后，将idx层数字标记为未被索引
        for (int i = idx; i < nums.length; i++) {
            swap(idx, i);
            dfs(idx + 1);
            swap(idx, i);
        }

    }

    /**
     * 交换两数的位置
     *
     * @param left
     * @param right
     */
    private void swap(int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

}
