package com.vista.drill.xiaochao.高频题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 * @author Wen TingTing by 2021/2/18
 */
public class 用最少数量的箭引爆气球 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int count = 1;
        int end = points[0][1];
        for (int[] point : points) {
            int start=point[0];
            if(start>end){
                count++;
                end=point[1];
            }
        }
        return count;

    }

    public static void main(String[] args) {
        new 用最少数量的箭引爆气球().findMinArrowShots(new int[][]{new int[]{-2147483646,-2147483645},new int[]{2147483646,
                2147483647}});
    }
}