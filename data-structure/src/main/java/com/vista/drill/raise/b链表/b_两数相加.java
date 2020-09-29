package com.vista.drill.raise.b链表;

/**
 * 2.两数相加
 * 2个非空链表标识连个2个非负的正数。各自位数按照逆序方式存储，并且一个节点仅存储一位数字。
 * 将两个数相加，返回一个新的链表表示它们的和
 * <p>
 * 设置2个指针、1个变量：
 * dummyHead:新链表的虚拟头结点
 * last：新链表的尾结点
 * carry：进位值
 * l1、l2：遍历两个链表
 * 初始状态：dummyHead=last=new Node(0)
 *
 * <p>
 * 返回结果：dummyHead.next
 * https://leetcode-cn.com/problems/add-two-numbers/submissions/
 *
 * @author WenTingTing by 2020/9/29
 */
public class b_两数相加 {
    /**
     * - 遍历两个链表，将两个节点值与进位值相加；
     * - 创建新节点存储个位值，十位值存储在carray中
     * - 新链表添加新节点:last.next=newNode(..) ;last指向新节点
     * - 若两链表中有一个结点为null，视为0
     * - 若两链表结点均为null时，先判断是否有进位(若存在，创建新结点存储进位值，并将结点加入到链表中)，然后退出
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 创建虚拟头结点、尾结点，进位值
        ListNode dummyHead = new ListNode(0);
        ListNode last = dummyHead;
        int carry = 0;


        while (l1 != null || l2 != null) {
            int v1 = 0;
            int v2 = 0;
            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            int result = v1 + v2 + carry;
            ListNode newNode = new ListNode(result % 10);
            last.next = newNode;
            last = newNode;
            carry = result / 10;
        }

        if (carry > 0) last.next = new ListNode(carry);
        return dummyHead.next;
    }
}
