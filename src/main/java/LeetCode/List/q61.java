package LeetCode.List;

public class q61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0)
            return head;
        ListNode ans = head;
        int l = 0;
        while (head != null) {
            head = head.next;
            l++;
        }

        for (int i = 1; i <= k % l; i++) {
            ans = rotateRight(ans);
        }
        return ans;
    }

    public ListNode rotateRight(ListNode head) {

        ListNode f = head;
        ListNode pre = head;
        ListNode cur = head;
        while (cur.next != null) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = null;
        cur.next = head;
        return cur;
    }

}
