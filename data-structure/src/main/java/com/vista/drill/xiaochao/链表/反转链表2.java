package com.vista.drill.xiaochao.链表;

/**https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * @author Wen TingTing by 2021/2/9
 */
public class 反转链表2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(m==1){
            return reverseN(head,n);
        }
        head.next=reverseBetween(head.next,m-1,n-1);
        return head;

    }

    ListNode successor=null;
    private ListNode reverseN(ListNode head, int n) {
        if(n==1){
            successor=head.next;
            return head;
        }
        ListNode res=reverseN(head.next,n-1);
        head.next.next=head;
        head.next=successor;
        return res;
    }
}
