package com.vista.drill.xiaochao.高频题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 *
 * @author Wen TingTing by 2021/2/18
 */
public class 无重叠区间 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int end = intervals[0][0];
        int count = 0;
        for (int[] inter : intervals) {
            int start = inter[0];
            if (start < end) {
                count++;
            }else{
                end=inter[1];
            }
        }
        return count;

    }
}
