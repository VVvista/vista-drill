package com.vista.drill.raise.h高频题;

import java.util.Arrays;
import java.util.List;

/**
 * 会议室
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
 * 请你判断一个人是否能够参加这里面的全部会议。
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 *
 * @author WenTingTing by 2020/12/28
 */
public class h_会议室 {
    /**
     * 按照会议的开始时间进行排序，循环遍历数组
     * - 若会议i的开始时间< 会议i-1的结束时间，则返回false
     *
     * @param list
     * @return
     */
    public boolean canAttendMeetings(int[][] list) {

        if (list == null || list.length == 0) return true;
        // 按照会议的开始时间从小到大排序
        Arrays.sort(list, (h1, h2) -> h1[0] - h2[0]);
        // 循环遍历会议
        for (int i = 1; i < list.length; i++) {
            if (list[i][0] < list[i - 1][1]) return false;
        }
        return true;

    }
}
