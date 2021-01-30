package com.vista.drill.raise.h高频题;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 会议室2
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，
 * 请你计算至少需要多少间会议室，才能满足这些会议安排
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 *
 * @author WenTingTing by 2020/12/28
 */
public class i_会议室2 {
    /**
     * 方法1：最小堆（存放会议的结束时间）
     * 循环遍历所有会议
     * - 若会议i的开始时间> 堆顶的时间，删除堆顶，将会议i的结束时间加入堆中(会议室可利用)
     * - 若会议i的开始时间< 堆顶的时间，直接将会议i的结束时间加入对中(重开会议室)
     * - 最终堆的大小即为会议室个数
     * <p>
     * 注意： 最小堆： 堆顶-> 堆底 ，小->大
     *
     * @param intervals
     * @return
     */
    public int canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        // 按照会议开始时间排序
        Arrays.sort(intervals, (h1, h2) -> h1[0] - h2[0]);
        // 创建最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 会议室个数
        int room = 0;
        // 循环遍历每个会议
        for (int i = 0; i < intervals.length; i++) {
            // 获取堆顶元素
            int peek = minHeap.peek();
            // 重开会议室
            if (intervals[i][0] < peek) {
                minHeap.remove();
                room++;
            }
            minHeap.add(intervals[i][1]);
        }
        return room;
    }


    /**
     * 方法2： 分开排序
     * 将所有的会议的开始时间、结束时间组成数组，并分别从小到大排序
     * 设置两个指针 beginIndex、endIndex
     * -循环遍历开始数组beginIndex
     * -结束数组endIndex
     * - 循环遍历开始数组，若begin[beginIndex] <= end[endIndex] ,会议室room++，beginIndex++
     * - 若begin[beginIndex] > end[endIndex] ,endIndex++ ，beginIndex++
     *
     * @param intervals
     * @return
     */
    public int canAttendMeetings2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        // 按照会议开始时间排序
        Arrays.sort(intervals, (h1, h2) -> h1[0] - h2[0]);
        // 会议室个数
        int room = 0;
        // 循环遍历每个会议

        return room;
    }

}
