package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode f = l1.val > l2.val ? l2 : l1;
        ListNode s = l1.val > l2.val ? l1 : l2;
        ListNode cur = f;
        f = f.next;
        ListNode ans = cur;
        cur.next = null;
        while (f != null && s != null) {
            if (f.val < s.val) {
                cur.next = f;
                f = f.next;
                cur = cur.next;
                cur.next = null;
            } else {
                cur.next = s;
                s = s.next;
                cur = cur.next;
                cur.next = null;
            }
        }
        if (f != null)
            cur.next = f;

        if (s != null)
            cur.next = s;

        return ans;
    }


}
