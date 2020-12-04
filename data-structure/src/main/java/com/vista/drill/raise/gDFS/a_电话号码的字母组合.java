package com.vista.drill.raise.gDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @author WenTingTing by 2020/12/4
 */
public class a_电话号码的字母组合 {
    // 所有字符
    char[][] lettersArray = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j','k', 'l',}, { 'm','n', 'o'},{'p',
            'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'},};

    // 所有字母组合
    List<String> list;
    // 字符串集合
    char[] chars;
    // 每一种字母组合结果
    char[] string;
    // 循环深度== chars.length
    int deepth;

    /**
     * 获取字符串表示的所有字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == null) return null;
        // 获取字符串集合
        chars = digits.toCharArray();
        // 字符串长度，即循环深度
        deepth = chars.length;
        list = new ArrayList<>();
        if (deepth == 0) return list;
        string = new char[deepth];
        // 开始循环
        dfs(0);
        // 返回结果
        return list;
    }

    /**
     * 正在探索第idx层
     *
     * @param idx
     */
    private void dfs(int idx) {
        // 探索层数==深度，探索结束，将字母组合string添加进list中
        if (idx == deepth) {
            list.add(new String(string));
            return;
        }

        // 遍历第idx层字母，将该层字母加入string中，并向下探索第idx+1层；
        char[] letters = lettersArray[chars[idx] - '2'];
        for (char letter : letters) {
            string[idx] = letter;
            dfs(idx + 1);
        }
    }

}
