package com.vista.drill.raise.gDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列2
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 思路：b_全排列_2 基础上，如果[idx,i)范围内存在于nums[i]相同的数字，则不进行交换
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * @author WenTingTing by 2020/12/16
 */
public class c_全排列 {
    // 所有可能全排列组合
    public static List<List<Integer>> result;

    // 给定数字
    public static int[] nums;

    // 全排列深度
    public static int deepth;

    public static List<List<Integer>> permuteUnique(int[] numss) {
        if (numss == null) return null;
        result = new ArrayList<>();
        nums = numss;
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
    public static void dfs(int idx) {
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
            // 若存在相同数字，则跳过该处理逻辑
            if (isEquals(idx, i)) {
                continue;
            }
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
    public static void swap(int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /**
     * 判断[left,right)范围内是否有数字与nums[right]相同
     *
     * @param left
     * @param right
     * @return
     */
    public static boolean isEquals(int left, int right) {
        for (int i = left; i < right; i++) {
            if (nums[i] == nums[right]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
    }

}
