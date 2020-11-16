package com.vista.drill.raise.d串;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 * 思路：values[i]: 以i结尾的最长无重复字符的子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @author Wen TingTing by 2020/11/15
 */
public class e_无重复字符的最长子串 {
    /**
     * 方法1： 动态规划
     * 1.设置个指针： i、values[]
     * - i:循环遍历数组元素
     * - values[i]: 以i结尾的最长无重复字符的子串
     * - pi: chars[i-1]最长子串的初始位置： pi=i-values[i-1]
     * 动态规划：
     * - 初始状态： values[0]=1
     * - 状态转移方程： i属于[1,chars.length)
     * 1.循环遍历pi [pi,i)，pi++
     * 2.若chars[pi]==chars[i],values[i]== i-pi;退出pi循环
     * 3.若pi循环完，pi==i,values[i]=values[i-1]+1
     * 4.循环遍历values找出最大值
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        char[] chars = s.toCharArray();
        // 存储以chars[i]结尾的最长无重复子串的长度
        int[] values = new int[s.length()];

        values[0] = 1;
        // 循环字符
        for (int i = 1; i < chars.length; i++) {
            // 获取前一个字符chars[i-1]最长无重复子串的初始位置
            int pi = i - values[i - 1];
            // [pi,i)内chars[i]重复字符的位置，且一定只要一个位置，无需纠结是否为首位置
            for (; pi < i; pi++) {
                // 若查找到，则[重复位置+1,i]长度即为chars[i]的最长子串
                if (chars[pi] == chars[i]) {
                    values[i] = i - pi;
                    break;
                }
            }
            // 结束循环，若pi==i，说明未找到与char[i]相同的字符，即char[i]最长子串在i-1的结果上+1
            if (pi == i) {
                values[i] = values[i - 1] + 1;
            }
        }
        // 寻找最大的最长子串长度
        // 无重复子串的最大值
        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            max = Math.max(max, values[i]);
        }
        return max;
    }

    /**
     * 方法2： 方法1优化
     * 1.chars[i]只与chars[i-1]状态有关，所以只需设置变量values存储上一次字符的最长子串长度即可
     * 2.确定chars[i]的values后，即可查找截止当前的最大子串长度 Math.max(max,values)
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        char[] chars = s.toCharArray();
        // 以chars[i-1]字符结尾的最长子串的长度，初始值代表chars[0]的最长子串长度1
        int values = 1;
        // 无重复字符的最长子串的长度，初始以chars[0]的最长子串长度1
        int max = values;
        for (int i = 1; i < chars.length; i++) {
            int pi = i - values;
            for (; pi < i; pi++) {
                if (chars[pi] == chars[i]) {
                    values = i - pi;
                    break;
                }
            }
            if (pi == i) {
                values = values + 1;
            }
            max = Math.max(max, values);
        }
        return max;
    }
}
