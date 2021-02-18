package com.vista.drill.xiaochao.栈队列;


import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 *
 * @author Wen TingTing by 2021/2/6
 */
public class 下一个更大元素2 {
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            ans[i]=-1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length * 2 - 1; i++) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                ans[stack.peek()] = nums[i % n];
                stack.pop();
            }
            stack.push(i % n);
        }
      /*  while (!stack.isEmpty()) {
            ans[stack.peek()] = -1;
            stack.pop();
        }*/
        return ans;
    }

    public int[] nextGreaterElements2(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = nums.length * 2 - 2; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i % n] >= nums[stack.peek()]) {
                stack.pop();
            }
            ans[i % n] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % n);
        }
        return ans;
    }
}
