package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q148 {

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    public ListNode mergeSort(ListNode head) {

        if (head == null || head.next == null)
            return head;
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cut = slow;
        pre.next = null;
        return mergeTwoLists(mergeSort(head), mergeSort(cut));
    }

    public ListNode mergeTwoListsUsingdummy(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
            cur.next = null;
        }
        if (l1 != null)
            cur.next = l1;
        if (l2 != null)
            cur.next = l2;
        return dummy.next;
    }

    public ListNode mergeSortUsingetsize(ListNode head) {
        ListNode pre = head;
        ListNode cur = head;
        if (head == null || head.next == null)
            return head;

        int len = getsize(head);

        int i = 0;
        while (i < len / 2) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        pre.next = null;
        return mergeTwoLists(mergeSortUsingetsize(head), mergeSortUsingetsize(cur));
    }

    public int getsize(ListNode head) {
        if (head == null)
            return 0;
        int ans = 0;
        while (head != null) {
            head = head.next;
            ans++;
        }
        return ans;
    }

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
            } else {
                cur.next = s;
                s = s.next;

            }
            cur = cur.next;
            cur.next = null;
        }
        if (f != null)
            cur.next = f;

        if (s != null)
            cur.next = s;

        return ans;
    }

}
