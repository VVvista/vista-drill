package com.vista.drill.xiaochao.高频题;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author Wen TingTing by 2021/2/18
 */
public class 有效的括号 {
    public boolean isValid(String s) {
        Stack<Character> left = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(c=='('||c=='['||c=='{'){
                left.push(c);
            }else{
                if(!left.empty()&&leftOf(c)==left.peek()){
                    left.pop();
                }else{
                    return  false;
                }
            }
        }
        return left.empty();

    }

    private char leftOf(char c) {
        if(c==')') return '(';
        else if(c==']') return '[';
        else  return '{';
    }
}
