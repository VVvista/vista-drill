package com.vista.drill.xiaochao.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets/
 *
 * @author Wen TingTing by 2021/2/11
 */
public class 子集 {
    private List<List<Integer>> result;
    private List<Integer> list;
    int[] nums;

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        result = new ArrayList<>();
        list = new ArrayList<>();
        this.nums = nums;
        result.add(new ArrayList<>());
        subsets(0);
        return result;

    }

    public void subsets(int start) {
        if (start == nums.length) return;
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            subsets(i + 1);
            list.remove(list.size() - 1);
        }
    }

}
