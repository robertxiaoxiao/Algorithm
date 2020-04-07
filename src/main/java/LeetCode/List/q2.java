package LeetCode.List;

import java.util.List;

public class q2 {

    static int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        carry = 0;
        return helper(l1, l2);
    }

    public ListNode helper(ListNode l1, ListNode l2) {
        int val = carry;
        if (l1 == null && l2 == null) {
            if (carry > 0)
                return new ListNode(carry);
            else
                return null;
        }

        if (l1 != null) {
            val += l1.val;
        }
        if (l2 != null)
            val += l2.val;

        carry = val / 10;
        val %= 10;

        ListNode newnode = new ListNode(val);

        if (l1 != null && l2 == null) {
            newnode.next = helper(l1.next, null);
        } else if (l2 != null && l1 == null) {
            newnode.next = helper(l2.next, null);
        } else if (l2 != null && l1 != null)
            newnode.next = helper(l1.next, l2.next);

        return newnode;
    }

    public ListNode addTwoNumbersreverse(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode h1 = l1;
        ListNode h2 = l2;
        int k = 0;

        while (h1 != null && h2 != null) {
            h1 = h1.next;
            h2 = h2.next;
        }

        ListNode llist;
        ListNode slist;
        if (h1 == null) {
            llist = l2;
            slist = l1;
        } else {
            llist = l1;
            slist = l1;
        }

        while (h1 != null) {
            h1 = h1.next;
            k++;
        }
        while (h2 != null) {
            h2 = h2.next;
            k++;
        }
        ListNode ans;
        if (k > 0) {
            ListNode padding = new ListNode(0);
            ListNode tail = padding;
            k--;
            while (k > 0) {
                tail.next = new ListNode(0);
                tail = tail.next;
                k--;
            }
            tail.next = slist;
            ans = add(llist, padding);
        } else {
            ans = add(l1, l2);
        }

        if (carry > 0) {
            ListNode head = new ListNode(carry);
            head.next = ans;
            return head;
        }
        return ans;
    }

    public ListNode add(ListNode l1, ListNode l2) {

        if (l1 == null || l2 == null)
            return null;

        if (l1.next == null && l2.next == null) {
            int val = l1.val + l2.val + carry;
            carry = val % 10;
            val /= 10;
            ListNode ans = new ListNode(val);
            return ans;
        }

        ListNode rest = add(l1.next, l2.next);
        int curval = l1.val + l2.val + carry;
        int val = curval % 10;
        carry = curval / 10;
        ListNode ans = new ListNode(val);
        ans.next = rest;
        return ans;
    }

}
