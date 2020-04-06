package com.vista.leetcode.string;

/**
 * https://leetcode-cn.com/problems/split-a-string-in-balanced-strings/
 * 重点：贪心算法
 *
 * @author WenTingTing by 2020/3/31
 */
public class E_1221_分割平衡字符串 {
    /**
     * 暴力解法
     * 解题思路：
     * 1.定义两个变量rNum： R的个数 ；lNum：L的个数，初始化为0
     * 2.一次循环字符串s每个位置，如果为R，rNum++;如果为L，lNum++
     * 3.直到rNum==LNum，num++；rNum、LNum初始化为0
     * 4.继续进行循环
     *
     * @param s
     * @return
     */
    public int balancedStringSplit(String s) {
        int num = 0;
        int rNum = 0;
        int lNum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'L') {
                lNum++;
            } else if (c == 'R') {
                rNum++;
            }
            if (rNum == lNum) {
                num++;
                rNum = 0;
                lNum = 0;
            }
        }
        return num;
    }


    /**
     * 贪心算法
     * 利用一个变量num记录'L'('R')的数量，遍历字符串s，如果字符为'L'('R')，则num++，否则num--
     * 当num为0时，之前出现的'L'和'R'的数量必定相等，此时将记录子串数量的res++，遍历完后返回res即可
     *
     * @param s
     * @return
     */
    public int balancedStringSplit2(String s) {
        // 平衡因子，只有num=0时说明字符串相等
        int num = 0;
        // 记录子串个数
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'L') {
                num++;
            } else {
                num--;
            }
            if (res == 0) {
                res++;
            }
        }
        return res;
    }
}
