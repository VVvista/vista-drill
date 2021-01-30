package com.vista.drill.raise.h高频题;

/**
 * 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 注意：假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 * https://leetcode-cn.com/problems/reverse-integer/
 *
 * @author WenTingTing by 2020/12/21
 */
public class g_正数反转 {
    /**
     * 方法1： 一趟遍历
     * 将整数转换成字符数组，设置首尾指针 l、r，遍历循环整个数组，依次交换l、r 位置数字，直至l>r
     *缺点：效率低
     */

    /**
     * 方法2: 1234 -> 4321
     * 利用个位数乘法
     * 4321=(((4*10) +3) *10 +2) *10 +1
     * 思路： 循环递归
     * 问题： 整数反转之后有可能溢出
     *
     * @param x
     * @return
     */

    /**
     * 整数溢出解决方案1
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long result = 0;
        while (x != 0) {
            // 获取个位数
            int mod = x % 10;
            // 求取result
            result = (result * 10) + mod;
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
            x = x / 10;
        }
        return (int) result;
    }


}
