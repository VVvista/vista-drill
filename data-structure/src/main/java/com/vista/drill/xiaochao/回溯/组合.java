package com.vista.drill.xiaochao.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combinations/
 *
 * @author Wen TingTing by 2021/2/11
 */
public class 组合 {
    private List<List<Integer>> result;
    private List<Integer> list;
    int n;
    int k;

    public List<List<Integer>> combine(int n, int k) {
        if (n == 0 || k == 0) return null;
        result = new ArrayList<>();
        list = new ArrayList<>();
        this.n = n;
        this.k = k;
        combine(1);

        return result;
    }

    public void combine(int start) {
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            combine(i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        组合 combine = new 组合();
        combine.combine(4,2);
    }

}
