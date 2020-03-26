package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q206 {


    public ListNode reverseListnaive(ListNode head) {

        ListNode pre = head;
        if (head == null)
            return null;

        ListNode cur = head.next;
        head.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    ListNode ans;

    public ListNode reverseList(ListNode head) {
        ans = null;
        reverse(head);
        return ans;
    }

    public ListNode reverse(ListNode head) {
        if (head == null)
            return null;

        if (head.next == null) {
            ans = head;
            return head;
        }

        ListNode ans = reverse(head.next);
        head.next = null;
        ans.next = head;
        return head;
    }

}
