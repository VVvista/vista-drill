package com.vista.drill.xiaochao.回溯;

import java.util.ArrayList;
import java.util.List;

/**https://leetcode-cn.com/problems/permutations/
 * @author Wen TingTing by 2021/2/11
 */
public class 全排列 {
    private List<List<Integer>> result;
    private List<Integer> list;
    int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        result = new ArrayList<>();
        list = new ArrayList<>();
        this.nums = nums;
        permute();
        return result;
    }

    private void permute() {
        if(list.size()==nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(list.contains(nums[i])){
                continue;
            }
            list.add(nums[i]);
            permute();
            list.remove(list.size()-1);
        }
    }
}
