package com.vista.leetcode.list.string;

/**
 * @author WenTingTing by 2020/4/1
 */
public class E_1370_上升下降字符串 {
    public String sortString(String s) {
        int x = 0;
        int[] num = new int[26];
        for (char c : s.toCharArray()) {
            num[c - 'a']++;
        }
        StringBuffer buffer = new StringBuffer();
        while (x < s.length()/2+1) {
            for (int i = 0; i < num.length; i++) {
                if (num[i] > 0) {
                    buffer.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            for (int i = num.length - 1; i >= 0; i--) {
                if (num[i] > 0) {
                    buffer.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            x++;
        }
         return buffer.toString();
    }
}
