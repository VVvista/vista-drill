package com.vista.drill.raise.gDFS;

/**
 * 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 * @author WenTingTing by 2020/12/18
 */
public class g_两数相加 {
    /**
     * 遍历两个链表
     * - 设置变量 carry 存储进位数
     * - 设置新 ListNode 存储结果
     * -- 分别设置新链表的头指针、尾指针
     *
     * @param l1
     * @param l2
     * @return
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // 结果链表的头指针
        ListNode resultHead = new ListNode(0);
        // 结果链表的尾指针，便于添加新节点
        ListNode last = resultHead;
        // 进位数
        int carry = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            last.next = new ListNode(carry % 10);
            last = last.next;
            carry = carry / 10;
           /* 该赋值放在尾部可能因为l1=null时 , l1.next 报异常
           l1 = l1.next;
            l2 = l2.next;*/
        }
        // 若carry!=0,则加到尾部
        if (carry != 0) last.next = new ListNode(carry);
        return resultHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

}