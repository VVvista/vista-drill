package com.vista.leetcode.list.arrays;

/**
 * @author WenTingTing by 2020/4/2
 */
public class E_1295_统计位数为偶数的数字 {
    public int findNumbers(int[] nums) {
        int f = 0;
        for (int num : nums) {
            int length = String.valueOf(num).length();
            if (length % 2 == 0) {
                f++;
            }
        }
        return f;
    }
}
