package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/13/013
 * @description:
 */

import java.util.List;

public class q445 {


    public ListNode addTwoNumbersnaive(ListNode l1, ListNode l2) {
        ListNode rl1 = reverse(l1);
        ListNode rl2 = reverse(l2);
        ListNode res = sum(rl1, rl2);
        return reverse(res);
    }

    int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = getsize(l1);
        int len2 = getsize(l2);
        int diff = Math.abs(len1 - len2);
        ListNode longlist = len1 > len2 ? l1 : l2;
        ListNode head = longlist;
        ListNode shortlist = len1 > len2 ? l2 : l1;

        int idx = 1;
        while (idx < diff) {
            longlist = longlist.next;
            idx++;
        }
        ListNode cut;
        ListNode res;
        if (diff != 0) {
            cut = longlist.next;
            longlist.next = null;
            res = addTwonumsPadding(shortlist, cut);
            ListNode temp = updateOneNode(head);
            ListNode fans = temp;
            while (temp.next != null)
                temp = temp.next;
            temp.next = res;
            res = fans;
        } else {
            res = addTwonumsPadding(shortlist, head);
        }

        if (carry != 0) {
            ListNode ans = new ListNode(carry);
            ans.next = res;
            return ans;
        }
        return res;
    }

    public ListNode updateOneNode(ListNode l1) {
        if (l1 == null)
            return null;
        ListNode temp = new ListNode(0);
        temp.next = updateOneNode(l1.next);
        int val = (l1.val + carry) % 10;
        carry = (l1.val + carry) / 10;
        temp.val = val;
        return temp;
    }

    public ListNode addTwonumsPadding(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        ListNode temp = new ListNode(0);
        temp.next = addTwonumsPadding(l1.next, l2.next);
        int val = (l1.val + l2.val + carry) % 10;
        carry = (l1.val + l2.val + carry) / 10;
        temp.val = val;
        return temp;
    }

    public ListNode addTwoNumbersRec(ListNode l1, ListNode l2) {
        int len1 = getsize(l1);
        int len2 = getsize(l2);
        int diff = Math.abs(len1 - len2);
        ListNode longlist = len1 > len2 ? l1 : l2;
        ListNode shortlist = len1 > len2 ? l2 : l1;
        shortlist = padding(shortlist, diff);

        ListNode res = addTwonumsPadding(longlist, shortlist);
        if (carry != 0) {
            ListNode ans = new ListNode(carry);
            ans.next = res;
            return ans;
        }
        return res;
    }

    public int getsize(ListNode l1) {
        int a = 0;
        while (l1 != null) {
            a++;
            l1 = l1.next;
        }
        return a;
    }

    public ListNode padding(ListNode l1, int diff) {
        ListNode cur = l1;
        while (diff != 0) {
            ListNode t = new ListNode(0);
            t.next = cur;
            cur = t;
            diff--;
        }
        return cur;
    }


    public ListNode sum(ListNode l1, ListNode l2) {
        int p = 0;
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode head = new ListNode(0);
        ListNode ans = head;
        while (c1 != null && c2 != null) {
            int t = c1.val + c2.val + p;
            ListNode cur = new ListNode(t % 10);
            p = t / 10;
            head.next = cur;
            head = head.next;
            c1 = c1.next;
            c2 = c2.next;
        }
        while (c1 != null) {
            int t = c1.val + p;
            ListNode cur = new ListNode(t % 10);
            p = t / 10;
            head.next = cur;
            head = head.next;
            c1 = c1.next;
        }
        while (c2 != null) {
            int t = c2.val + p;
            ListNode cur = new ListNode(t % 10);
            p = t / 10;
            head.next = cur;
            head = head.next;
            c2 = c2.next;
        }

        if (p != 0) {
            head.next = new ListNode(p);
        }
        return ans.next;
    }

    public ListNode reverse(ListNode l1) {
        ListNode cur = l1;
        cur.next = null;
        ListNode next = l1.next;
        while (next != null) {
            ListNode tn = next.next;
            next.next = cur;
            cur = next;
            next = tn;
        }
        return cur;
    }


}
