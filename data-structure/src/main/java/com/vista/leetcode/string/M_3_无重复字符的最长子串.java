package com.vista.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Wen TingTing by 2020/4/5
 */
public class M_3_无重复字符的最长子串 {
    /**
     * 暴力解法
     * 依次循环str所有子串，如果字符不重复便将其存储在hashSet中
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        int num = 0;
        HashSet<Character> characters = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            characters.clear();
            for (int j = i; j < s.length(); j++) {
                if (characters.contains(s.charAt(j))) {
                    break;
                }
                characters.add(s.charAt(j));
            }
            num = Math.max(num, characters.size());
        }
        return num;
    }

    /**
     * 滑动窗口
     * 设立头指针flag=0
     * 1.依次循环str字符，并将字符及其索引存储在hashMap中
     * 2.如果字符重复，则将flag重置为：字符索引+1
     * 3.将字符加入map中，无重复字符的有效长度即为 i-flag+1
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int num = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int flag = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                flag = Math.max(flag, map.get(c) + 1);
            }
            map.put(c, i);
            num = Math.max(num, i - flag + 1);
        }
        return num;
    }


    public static void main(String[] args) {
        //  System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring2("pwwkew"));
        // System.out.println(lengthOfLongestSubstring2(" "));

    }

}
