package com.vista.drill.raise.d串;

/**
 * 3.有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 说明：你可以假设字符串只包含小写字母。
 * <p>
 * 思路：
 * 创建一个数组分别存储a-z字符出现的次数
 * - 循环遍历s字符，并在数组对应位置+1
 * - 循环遍历t字符，在数组的对应位置-1，若数组元素<0，则退出，两字符串不相等
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * @author WenTingTing by 2020/11/19
 */
public class c_有效的字母异位词 {

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();

        int[] result = new int[26];

        for (char ss : schars) {
            result[ss - 'a']++;
        }
        for (char tt : tchars) {
            if (--result[tt - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
