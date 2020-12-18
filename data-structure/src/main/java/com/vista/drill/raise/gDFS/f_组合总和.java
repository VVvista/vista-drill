package com.vista.drill.raise.gDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * <p>
 * 链接：https://leetcode-cn.com/problems/combination-sum
 *
 * @author WenTingTing by 2020/12/17
 */
public class f_组合总和 {
    // 最终所有组合的集合
    List<List<Integer>> result;
    // 每种组合的集合
    List<Integer> list = new ArrayList<>();
    int target;
    int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null) return null;
        result = new ArrayList<>();
        this.target = target;
        this.candidates = candidates;
        dfs(0);
        return result;

    }

    private void dfs(int idx) {
        if (target < 0) {
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            target -= candidates[i];
            list.add(candidates[i]);
            if (target == 0) {
                // 此处仅添加即可，无需 return ，否则后面的for不会执行
                result.add(new ArrayList<>(list));
            }
            dfs(i);
            target += candidates[i];
            list.remove(list.size() - 1);
        }

    }

}
