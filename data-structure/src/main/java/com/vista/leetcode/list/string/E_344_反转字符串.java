package com.vista.leetcode.list.string;

/**
 * https://leetcode-cn.com/problems/reverse-string/
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * @author WenTingTing by 2020/4/2
 */
public class E_344_反转字符串 {
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char tmp=s[i];
            s[i]=s[j];
            s[j]=tmp;
        }

    }
}
