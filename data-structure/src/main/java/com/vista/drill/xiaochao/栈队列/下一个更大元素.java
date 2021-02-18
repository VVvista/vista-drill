package com.vista.drill.xiaochao.栈队列;


import java.util.Arrays;
import java.util.Stack;

/**
 * @author Wen TingTing by 2021/2/6
 */
public class 下一个更大元素 {
    public int[] nextGreaterElement(int[] nums){
        int[] ans =new int[nums.length];
        Stack<Integer> stack=new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while(!stack.isEmpty()&&nums[i]>nums[stack.peek()]){
                ans[stack.peek()]=nums[i];
                stack.pop();
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            ans[stack.pop()]=-1;
        }
        return ans;
    }
    public int[] nextGreaterElement2(int[] nums){
        int[] ans =new int[nums.length];
        Stack<Integer> stack=new Stack<>();
        for (int i = nums.length-1; i >=0; i--) {
            while (!stack.isEmpty()&&nums[i]>=nums[stack.peek()]){
                stack.pop();
            }
            ans[i]=stack.empty()?-1:nums[stack.peek()];
            stack.push(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        下一个更大元素 nums = new 下一个更大元素();
        int[] ans = nums.nextGreaterElement(new int[]{2, 1, 2, 4, 3});
        System.out.println(Arrays.toString(ans));
        int[] ans2 = nums.nextGreaterElement2(new int[]{2, 1, 2, 4, 3});
        System.out.println(Arrays.toString(ans2));
    }
}
