package com.vista.leetcode;

/**
 * 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * @author WenTingTing by 2020/12/22
 */
public class 回文数 {
    /**
     * 方法1：将整数转为字符串
     * 设置首尾指针l、r，依次循环比较
     * -
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        char[] nums = Integer.toString(x).toCharArray();
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            if (nums[l] != nums[r]) return false;
            l++;
            r--;
        }
        return true;
    }


    /**
     * 方法2：数学解法
     *
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        if (x < 0) return false;
        // x位数-1
        int div = 1;
        while (x / 10 != 0) div++;
        int a = x % 10;
        int b = x / 10;


        return true;
    }
}
