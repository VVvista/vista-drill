package com.vista.drill.raise.e动态规划;

/**
 * 5.最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @author WenTingTing by 2020/11/23
 */
public class e_最长回文子串 {
    /**
     * 方法1： 动态规划
     * dp(i)(j): s[i,j]是否为回文子串，仅true ，false。j>=i
     * 状态转移方程：
     * - 1.若s[i,j]长度<=2, dp(i)(j)= s[i]==s[j]
     * - 2.若s[i,j]长度>2, dp(i)(j)= s[i]==s[j]&& dp(i+1)(j-1)
     * 注意： dp(i)(j)是上三角矩阵，计算从左到右，从下到上
     * 时间复杂度： o(n^2); 空间复杂度 ： o(n^2)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        int length = s.length();
        char[] chars = s.toCharArray();

        // 记录最大的回文子串长度
        int maxLen = 0;
        // 回文子串的开始位置
        int begin = 0;
        boolean[][] dp = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                // 子串的有效长度
                int len = j - i + 1;
                // 判断是否为回文子串
                dp[i][j] = len <= 2 ? chars[i] == chars[j] : ((chars[i] == chars[j]) && dp[i + 1][j - 1]);
                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 方法2：扩展中心法
     * - 以字符i为中心向两边扩展，判断子串是否为回文子串
     * - 以间隙为中心向两边扩展，判断子串是否为回文子串
     * <p>
     * 注： 该方法真是又臭又难搞
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        int length = s.length();
        char[] chars = s.toCharArray();

        // 记录最大的回文子串长度,
        int maxLen = 1;
        // 回文子串的开始位置
        int begin = 0;

        // 以字符i为中心向两边扩展，判断子串是否为回文子串
        for (int i = 1; i < length - 1; i++) {
            // 注意下角标越界
            for (int j = 1; j <= Math.min(i, length - 1 - i); j++) {
                if (chars[i - j] != chars[i + j]) {
                    break;
                }
                if ((2 * j + 1) > maxLen) {
                    maxLen = 2 * j + 1;
                    begin = i - j;
                }
            }
        }

        // 以间隙为中心向两边扩展，判断子串是否为回文子串
        for (double i = 0.5; i < length - 0.5; i++) {
            for (double j = 0.5; j <= Math.min(Math.ceil(i), Math.ceil(length - 0.5 - i)); j++) {
                if (chars[(int) (i - j)] != chars[(int) (i + j)]) {
                    break;
                }
                if ((2 * j + 1) > maxLen) {
                    maxLen = (int) (2 * j + 1);
                    begin = (int) (i - j);
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 方法2：扩展中心法
     * - 以字符i为中心向两边扩展，判断子串是否为回文子串
     * - 以间隙为中心向两边扩展，判断子串是否为回文子串
     * 实现：从后往前,循环遍历 i [length-2,1] 和i+0.5 向两边扩展可得到的最大回文子串长度
     * 时间复杂度： o(n^2); 空间复杂度 ： o(1)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        int length = s.length();
        char[] chars = s.toCharArray();

        // 记录最大的回文子串长度,
        int maxLen = 1;
        // 回文子串的开始位置
        int begin = 0;

        // 从后往前循环遍历字符i，并查找 以i为中心 或以i+0.5(i的右边间隙)为中心 向两边扩展，判断子串是否为回文子串
        for (int i = length - 2; i >= 1; i--) {
            //  以i为中心 向两边扩展
            int iLength = longest(chars, i - 1, i + 1);
            //  以i+0.5为中心 向两边扩展
            int irLength = longest(chars, i, i + 1);
            // 最大回文子串长度
            int max = Math.max(iLength, irLength);
            if (max > maxLen) {
                maxLen = max;
                // 开始位置=i-回文子串长度的一半
                begin = i - ((max - 1) >> 1);
            }
        }
        // 判断0.5位置为中心向两边扩展的回文子串，注意此时只需比较[0,1]即可
        if (chars[0] == chars[1] && 2 > maxLen) {
            // 若[0,1]是回文子串，且长度>maxLen，直接返回[0,1]
            return s.substring(0, 2);
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 以[l,r]向两边扩展，获取的最大回文子串的长度
     * 注意：循环结束时，有效的回文子串[l+1,r-1]
     *
     * @param chars
     * @param l
     * @param r
     * @return
     */
    public static int longest(char[] chars, int l, int r) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
            l--;
            r++;
        }
        return r - l - 1;

    }

    public static void main(String[] args) {
        // System.out.println(longestPalindrome2("babad"));
        System.out.println(longestPalindrome5("babad"));

    }

    /**
     * 方法3： 基于扩展中心法的优化
     * 在方法2的基础上，由连续的相同字符组成的子串作为扩展中心
     * 思路： 设置3个指针
     * - i: 字符
     * - l: i字符左边第一个字符
     * - r: i字符右边第一个 与其不相等的字符
     * 解题步骤：
     * - 循环遍历字符i 属于[0,s.length-1]
     * - 找到i右边第一个不相等字符r，比较l、r是否相等： 若相等 l--,r++；不相等则得出有效的最长子串长度
     * - i重新赋值：i=r(i右边第一个不相等字符)
     * - 直至循环结束
     *
     * @param s
     * @return
     */
    public static String longestPalindrome4(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;
        int length = s.length();
        char[] chars = s.toCharArray();

        // 记录最大的回文子串长度,
        int maxLen = 1;
        // 回文子串的开始位置
        int begin = 0;

        // 循环遍历i
        int i = 0;
        while (i < chars.length) {
            // 查找右边第一个不相等元素r
            int r = i;
            while (r < chars.length && chars[r] == chars[i]) {
                r++;
            }

            // 定义变量l
            int l = i - 1;
            // i重新赋值
            i = r;

            // 循环比较l、r是否相等,循环结束时l、r字符不相等，有效区间为[l+1,r-1]
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                l--;
                r++;
            }

            // 比较有效长度与maxLen大小
            if (maxLen < (r - l - 1)) {
                maxLen = r - l - 1;
                begin = l + 1;
            }

        }
        return s.substring(begin, begin + maxLen);

    }

    /**
     * 方法4： 马拉车算法
     * 思路：
     * - 填充字符串，创建新字符串 c：首尾分别填充字符^、$，每个字符间填充字符 # (填充字符必须与原字符不同)
     * - 创建数组 m[i]: c中以i为扩展中心的最大回文子串(不含# 字符)；或 c中以i为扩展中心的最大回文子串的右半部分/ 左半部分的长度(含# 字符)
     * 最大回文子串在原字符中的开始索引：(i-m[i])/2
     * 设置5个指针： l、li、c、i、r
     * 利用回文子串的对称性
     * - l、c、r：以c为扩展中心的最长回文子串的最左边l、最右边r
     * - i：字符i 属于(c,r]
     * - li: 以c为中心，i的对称位置
     * *算法：
     * 利用回文串的对称性，利用m[li]简化字符的比较次数
     * - 若i+m[li]<r,则 m[i]=m[li],i++
     * - 若i+m[li]>=r,则以i为中心比较r+1及其之后的字符，算出m[i]
     * - 若i+m[i]>r,更新c、r：c=i ; i=c+1
     * - 若i==r,则仍然以i为扩展中心，计算m[i],直到i+m[i]>r
     * <p>
     * 注： 已计算完成
     *
     * @param s
     * @return
     */
    public static String longestPalindrome5(String s) {
        if (s == null || s.length() == 0) return null;
        if (s.length() == 1) return s;

        // 记录最大的回文子串长度,
        int maxLen = 0;
        // 回文子串的开始位置
        int begin = 0;

        // 创建新字符串：原字符首尾、中间添加字符
        int newLen = (s.length() << 1) + 3;
        char[] chars = new char[newLen];
        chars[0] = '^';
        chars[1] = '#';
        chars[newLen - 1] = '$';
        for (int i = 0; i < s.length(); i++) {
            chars[(i + 1) << 1] = s.charAt(i);
            chars[((i + 1) << 1) + 1] = '#';
        }


        int[] m = new int[newLen];
        // 初始化c： 根据新字符串的特点，c可从1开始
        int c = 1;// m[1]=0
        int r = 1;
        int lastIndex = m.length - 2;
        int idx = 0;
        // 循环遍历i
        for (int i = 2; i < lastIndex; i++) {
            if (r > i) {
                int li = (c << 1) - i;
                // 判断i+m[li]与r关系
                if (i + m[li] <= r) {
                    m[i] = m[li];
                } else {
                    m[i] = r - i;
                }
            }

            // 以i为扩展中心从r+1开始判断字符是否相等，循环结束时，有效长度[l+1,r-1]
            while (chars[i + m[i] + 1] == chars[i - m[i] - 1]) {
                m[i]++;
            }

            // 比较i+len与r的关系,更新c、r
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }
            // 判断最大回文子串
            if (m[i] > maxLen) {
                maxLen = m[i];
                idx = i;
            }

        }
        begin = (idx - maxLen) >> 1;
        return s.substring(begin, begin + maxLen);

    }


}
