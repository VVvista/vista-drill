package com.vista.drill.raise.gDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * @author WenTingTing by 2020/12/4
 */
public class b_全排列 {
    // 所有可能全排列组合
    List<List<Integer>> result;

    // 每种全排列组合
    int[] list;

    // 给定数字
    int[] nums;

    // 全排列深度
    int deepth;

    //数字是否被索引到
    boolean[] booleans;

    /**
     * 计算给定数字的所有可能的全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) return null;
        deepth = nums.length;
        result = new ArrayList<>();
        if (deepth == 0) return result;
        // 数字赋值
        this.nums = nums;
        list = new int[deepth];
        booleans = new boolean[deepth];
        // 列举所有的全排序组合
        dfs(0);

        // 返回结果
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
            for (int i = 0; i < list.length; i++) {
                resultList.add(list[i]);
            }
            result.add(resultList);
        }

        // 循环遍历所有数字，若数字已经被索引，则跳过，否则将数字加入list中，再向下探索idx+1层
        // 探索完成后，将idx层数字标记为未被索引
        for (int i = 0; i < nums.length; i++) {
            if (booleans[i]) continue;
            list[idx] = nums[i];
            booleans[i] = true;
            dfs(idx + 1);
            booleans[i] = false;
        }

    }

    public static void main(String[] args) {

    }

}
