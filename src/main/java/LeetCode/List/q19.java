package LeetCode.List;

public class q19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode pre = head;
        ListNode cur = head;
        ListNode last = head;
        int k = n;
        while (cur != null && k > 0) {
            cur = cur.next;
            k--;
        }

        if (cur == null) {
            if (k == 0)
                return head.next;
            if (k > 0)
                return head;
        }

        while (cur != null) {
            pre = last;
            last = last.next;
            cur = cur.next;
        }

        pre.next = last.next;
        return head;
    }

}
