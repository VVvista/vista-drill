package com.vista.drill.xiaochao.高频题;

import com.vista.drill.xiaochao.回溯.煎饼排序;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/super-pow/
 *
 * @author Wen TingTing by 2021/2/16
 */
public class 超级次方 {
    int base = 1337;

    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) return 1;
        int last = b[b.length - 1];
        int[] newb = Arrays.copyOfRange(b,0,b.length-1);
        System.out.println(Arrays.toString(newb));
        int part1 = mypow(a, last);
        int part2 = mypow(superPow(a, newb), 10);
        return part1 * part2 % base;

    }

    private int mypow(int a, int k) {
        a %= base;
        int res = 1;
        for (int i = 1; i <= k; i++) {
            res *= a;
            res %= base;
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(new 超级次方().superPow(2,new int[]{3,1}));
        System.out.println(Math.ceil((double) 3/2));

    }
}
