package com.vista.drill.xiaochao.链表;

/**
 * @author Wen TingTing by 2021/2/8
 */
public class 回文链表2 {
    /**
     * 查找中间结点->反转链表->判断回文
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.next.val == head.val;
        ListNode mid = middleNode(head);
        ListNode right = reverseList(mid);
        ListNode left = head;
        while (right != null) {
            if (right.val != left.val)
                return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }


    /**
     * 查找中间结点：返回左右链表相同部分的尾部
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        if (fast != null) slow = slow.next;
        return slow;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = tmp;
        }

        return newHead;
    }
}