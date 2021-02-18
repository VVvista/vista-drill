package com.vista.drill.xiaochao.栈队列;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @author Wen TingTing by 2021/2/6
 */
public class 滑动窗口最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        MonotonicDeque window = new MonotonicDeque();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                window.push(nums[i]);
                res[i - k + 1] = window.max();
                window.pop(nums[i - k + 1]);

            }
        }
        return res;
    }

    class MonotonicDeque {
       Deque<Integer> q= new LinkedList<Integer>();

        public void push(int num) {
            while(!q.isEmpty()&&num>q.getLast()){
                q.pollLast();
            }
            q.addLast(num);

        }

        public Integer max() {

            return  q.getFirst();
        }

        public void pop(int num) {
            if(q.getFirst()==num){
                q.pollFirst();
            }

        }
    }
}


