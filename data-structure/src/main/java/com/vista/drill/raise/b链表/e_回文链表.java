package com.vista.drill.raise.b链表;

/**
 * 5.回文链表
 * 思路：找到链表的中间结点，然后将中间结点右侧链表翻转，最后遍历比较左右链表元素是否相同
 * 实现：
 * - 中间结点： 使用快慢指针实现
 * - 翻转链表：翻转中间结点右边链表
 * - 比较左右链表中的元素，右边链表为null或元素不相同，则退出
 * https://juejin.im/post/6844904118394290190#heading-19
 * https://leetcode-cn.com/problems/palindrome-linked-list-lcci/submissions/
 *
 * @author WenTingTing by 2020/10/19
 */
public class e_回文链表 {
    /**
     * 思路：找到链表的中间结点，然后将中间结点右侧链表翻转，最后遍历比较左右链表元素是否相同
     * 实现：
     * - 中间结点： 使用快慢指针实现
     * - 翻转链表：翻转中间结点右边链表
     * - 比较左右链表中的元素，右边链表为null或元素不相同，则退出
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        if (head.next.next == null) return head.next.val == head.val;

        // 查找中间结点
        ListNode middle = middleNode(head);

        // 翻转中间结点右边链表
        ListNode rightHead = reverseList(middle.next);
        ListNode leftHead = head;

        // 比较左右链表元素
        while (rightHead != null) {
            if (rightHead.val != leftHead.val) return false;
            rightHead = rightHead.next;
            leftHead = leftHead.next;
        }
        return true;
    }

    /**
     * 查找中间结点
     *
     * @param node
     * @return
     */
    private ListNode middleNode(ListNode node) {

        ListNode fast = node;
        ListNode slow = node;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 翻转链表
     *
     * @param node
     * @return
     */
    private ListNode reverseList(ListNode node) {
        ListNode newHead = null;
        while (node != null) {
            ListNode tmp = node.next;
            node.next = newHead;
            newHead = node;
            node = tmp;
        }
        return newHead;
    }


}
