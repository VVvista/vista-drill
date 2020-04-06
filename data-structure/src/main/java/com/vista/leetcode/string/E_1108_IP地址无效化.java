package com.vista.leetcode.string;

/**
 * https://leetcode-cn.com/problems/defanging-an-ip-address/
 *执行效率：defangIPaddr3 > defangIPaddr > defangIPaddr2
 * @author WenTingTing by 2020/3/31
 */
public class E_1108_IP地址无效化 {
    /**
     * 此题主要考察两个知识点
     * 表层的知识点是简单的字符串替换
     * 隐藏的知识点是考察了在字符串替换中可能需要用到的正则表达式，由于正则表达中 "." 是一个特殊字符，所以在替换IP地址中的 "." 时需要转义。除了 "."，正则表达式中的特殊字符还有 ".$|()[{^?*+\" 等等
     *
     * @param address
     * @return
     */
    public String defangIPaddr(String address) {
        String[] split = address.split("\\.");
        StringBuffer buffer = new StringBuffer();
        for (String s : split) {
            buffer.append(s).append("[.]");
        }
        return buffer.substring(0, buffer.length() - 3);
    }

    public String defangIPaddr2(String address) {
        return address.replaceAll("\\.", "[.]");
    }

    public String defangIPaddr3(String address) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == '.') {
                buffer.append("[.]");
            } else {
                buffer.append(address.charAt(i));
            }
        }
        return buffer.toString();
    }
}
