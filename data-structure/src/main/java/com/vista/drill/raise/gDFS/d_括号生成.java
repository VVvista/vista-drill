package com.vista.drill.raise.gDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @author WenTingTing by 2020/12/16
 */
public class d_括号生成 {
    // 最终所有有效括号的集合
    List<String> list;
    // 左括号起始个数
    int left;
    // 右括号起始个数
    int right;
    // 每种有效组合的集合
    char[] str;
    int deepth;

    public List<String> generateParenthesis(int n) {
        list = new ArrayList<>();
        if (n <= 0) return list;
        left = n;
        right = n;
        deepth = n << 1;
        str = new char[deepth];
        dfs(0);
        return list;
    }

    private void dfs(int idx) {
        if (deepth == idx) {
            list.add(String.valueOf(str));
            return;
        }
 /* 思路1:
     // 若left>0，则可选择左括号
        if (left > 0) {
            str[idx] = '(';
            left--;
            // 进行下一轮
            dfs(idx + 1);
            left++;
        }
        if (right > 0 && left != right) {
            str[idx] = ')';
            right--;
            // 进行下一轮
            dfs(idx + 1);
            right++;
        }
*/
        //思路2：
        // 若left>0，则可选择左括号
        if (left > 0 || left == right) {
            str[idx] = '(';
            left--;
            // 进行下一轮
            dfs(idx + 1);
            left++;
        }
        if (right > 0 && right > left) {
            str[idx] = ')';
            right--;
            // 进行下一轮
            dfs(idx + 1);
            right++;
        }
    }


}
