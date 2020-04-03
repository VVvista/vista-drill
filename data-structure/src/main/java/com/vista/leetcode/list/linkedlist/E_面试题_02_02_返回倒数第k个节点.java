package com.vista.leetcode.list.linkedlist;

/**https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/
 * @author WenTingTing by 2020/3/31
 */
public class E_面试题_02_02_返回倒数第k个节点 {
    /**
     * https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/solution/tu-jie-shuang-zhi-zhen-you-zou-javachao-guo-100de-/
     * 双指针：
     * 初始时，两个指针均指向 head。
     * 先将 q 向后移动 k 次。此时p，q的距离为 k。
     * 同时移动 p，q, 直到 q 指向 nullptr。此时p->val即为答案。
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        // 设置两个指针，分别指向head
        ListNode p = head;
        ListNode q = head;
        // q指针向后移动K次
        for (int i = 0; i < k; i++) {
            q = q.next;
        }
        // p、q同时向后移动，直到q=null
        while (q != null) {
            q = q.next;
            p = p.next;
        }

        // 返回p
        return p.val;
    }
}
