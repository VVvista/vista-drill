package com.vista.leetcode.string;

/**
 * https://leetcode-cn.com/problems/reverse-string-ii/
 * 执行效率：reverseStr2>reverseStr1
 *
 * @author WenTingTing by 2020/4/2
 */
public class E_541_反转字符串II {
    /**
     * 暴力解法，遍历字符串
     * 1.如果字符串下标余数在[0,k]之间先将其放在StringBuffer中
     * 2.当余数为k时，将StringBuffer字符反转拼接在buffer中
     * 3.如果余数在(k,2k-1]时，直接拼接在buffer中
     * <p>
     * 此方式效率太慢
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr1(String s, int k) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer reverse = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int mod = (i + 1) % (2 * k);
            if (0 < mod && mod <= k) {
                reverse.append(c);
            } else {
                buffer.append(c);
            }
            if (mod == k) {
                buffer.append(reverse.reverse());
                reverse.setLength(0);
            }
        }
        buffer.append(reverse.reverse());
        return buffer.toString();
    }

    /**
     * 每次反转2k个块
     * 指针每次移动2k步
     * 每一步中将前k个数据进行反转
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr2(String s, int k) {
        char[] chars = s.toCharArray();
        for (int start = 0; start < chars.length; start += 2 * k) {
            int i = start;
            int j = Math.min(start + k - 1, chars.length - 1);
            while (i < j) {
                char tmp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = tmp;
            }
        }
        return String.valueOf(chars);
    }
}
