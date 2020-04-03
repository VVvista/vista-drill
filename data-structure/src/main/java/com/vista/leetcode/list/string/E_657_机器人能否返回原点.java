package com.vista.leetcode.list.string;

/**
 * https://leetcode-cn.com/problems/robot-return-to-origin/
 *
 * @author WenTingTing by 2020/4/1
 */
public class E_657_机器人能否返回原点 {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'R':
                    x++;
                    break;
                case 'L':
                    x--;
                    break;
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                default:
                    break;
            }
        }
        return x == 0 && y == 0;
    }
}
