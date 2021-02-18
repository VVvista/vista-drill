package com.vista.drill.xiaochao.回溯;

import java.util.ArrayList;
import java.util.List;

/**https://leetcode-cn.com/problems/generate-parentheses/
 * @author Wen TingTing by 2021/2/13
 */
public class 括号生成 {
    // 最终所有有效括号的集合
    List<String> result;
    // 左括号起始个数
    int left;
    // 右括号起始个数
    int right;
    // 每种有效组合的集合
    StringBuffer str;
    public List<String> generateParenthesis(int n) {
        if(n==0) return null;
        result=new ArrayList<>();
        left=n;
        right=n;
        str=new StringBuffer();
        generateParenthesis();
        return result;

    }

    private void generateParenthesis() {
        if(left<0|| right<0) return;
        if(left==0&&right==0){
            result.add(str.toString());
        }
        if(left>0||left==right){
            str.append("(");
            left--;
            generateParenthesis();
            left++;
            str.deleteCharAt(str.length()-1);

        }
        if(right>0&&left<right){
            str.append(")");
            right--;
            generateParenthesis();
            right++;
            str.deleteCharAt(str.length()-1);

        }

    }
}
