package com.vista.leetcode.list.string;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/unique-morse-code-words/
 * 例如:
 * 输入: words = ["gin", "zen", "gig", "msg"]
 * 输出: 2
 * 解释:
 * 各单词翻译如下:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * <p>
 * 共有 2 种不同翻译, "--...-." 和 "--...--.".
 *
 * @author WenTingTing by 2020/4/1
 */
public class E_804_唯一摩尔斯密码词 {
    public int uniqueMorseRepresentations(String[] words) {
        String[] str = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        HashSet<String> set = new HashSet<String>();
        for (String s : words) {
            StringBuilder builder = new StringBuilder();
            for (char c : s.toCharArray()) {
                builder.append(str[c - 'a']);
            }
            set.add(builder.toString());
        }
        return set.size();
    }
}
