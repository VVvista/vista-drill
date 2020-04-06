package com.vista.leetcode.string;

/**
 * https://leetcode-cn.com/problems/to-lower-case/
 *
 * @author WenTingTing by 2020/3/31
 */
public class E_709_转换成小写字母 {
    /**
     * 利用ASCII值大小写之间相差32的性质，遇到大写的字母，加上32就是相应的小写字母。
     *
     * @param str
     * @return
     */
    public String toLowerCase(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //if (c >= 'A' && c <= 'Z') {
            if (c >= 65 && c <= 90) {
                c += 32;
                builder.append(c);
            } else {
                builder.append(c);
            }
        }
        return builder.toString();

    }
}
