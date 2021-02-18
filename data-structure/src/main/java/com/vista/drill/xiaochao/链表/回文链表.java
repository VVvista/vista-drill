package com.vista.drill.xiaochao.链表;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 *
 * @author Wen TingTing by 2021/2/8
 */
public class 回文链表 {
    /**
     * 利用后序遍历
     *
     * @param head
     * @return
     */
    ListNode left;

    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    /**
     * 判断以right为根节点的链表是否为回文链表
     *
     * @param right
     * @return
     */
    private boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean res = traverse(right.next);
        res = res && (right.val == left.val);
        left = left.next;
        return res;
    }


}
