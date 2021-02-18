package com.vista.drill.xiaochao.栈队列;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 *
 * @author Wen TingTing by 2021/2/6
 */
public class 每日温度 {
    /**
     * 方法1： 从前往后，栈单调递增
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                ans[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * 方法2： 从后往前，栈单调递增
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = T.length - 1; i >= 0; i--) {
            while(!stack.isEmpty()&&T[i]>=T[stack.peek()]){
                stack.pop();
            }
            ans[i]=stack.isEmpty()?0:stack.peek()-i;
            stack.push(i);
        }
        return ans;
    }
}
