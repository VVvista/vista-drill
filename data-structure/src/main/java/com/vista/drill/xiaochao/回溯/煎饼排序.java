package com.vista.drill.xiaochao.回溯;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pancake-sorting/
 *
 * @author Wen TingTing by 2021/2/15
 */
public class 煎饼排序 {
    List<Integer> result;
    int[] arr;

    public List<Integer> pancakeSort(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        result = new LinkedList<>();
        this.arr = arr;
        pancakeSort(arr.length);
        return result;
    }

    /*
    前[0,n)最大值反转至最后
     */
    private void pancakeSort(int n) {
        if (n == 1) return;
        // 查找最大值下标
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        //反转前[0,maxIndex]
        reverse(maxIndex);
        result.add(maxIndex+1);
        //反转前[0,n-1]
        reverse(n-1);
        result.add(n);
        pancakeSort(n - 1);
    }

    /*
    反转前[0,n)个数
     */
    private void reverse(int index) {
        int left=0;
        int right=index;
        while (left<right){
            int tmp=arr[left];
            arr[left]=arr[right];
            arr[right]=tmp;
            left++;
            right--;
        }

    }

    public static void main(String[] args) {
        System.out.println(new 煎饼排序().pancakeSort(new int[]{3,2,4,1}));

    }
}
