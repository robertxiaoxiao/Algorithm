package LeetCode.List;/*
 * @author: Robert
 * @date:  2020/2/14/014
 * @description:
 */

public class q707 {

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    class MyLinkedList {

        ListNode head;

        /**
         * Initialize your data structure here.
         */
        public MyLinkedList() {
            head = new ListNode(-1);
        }

        /**
         * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
         */
        public int get(int index) {
            ListNode cur = head.next;
            int idx = 0;
            while (cur != null) {
                if (idx == index)
                    return cur.val;
                cur = cur.next;
                idx++;
            }
            return -1;
        }

        /**
         * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            ListNode t = new ListNode(val);
            t.next = head.next;
            head.next = t;
        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public void addAtTail(int val) {
            ListNode t = new ListNode(val);
            ListNode cur = head;
            ListNode pre = head;
            while (cur != null) {
                pre = cur;
                cur = cur.next;
            }
            pre.next = t;
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
         */
        public void addAtIndex(int index, int val) {
            ListNode t = new ListNode(val);
            ListNode cur = head.next;
            ListNode pre = head;
            int idx = 0;
            while (cur != null) {
                if (idx == index) {
                    t.next = pre.next;
                    pre.next =  t;
                    return;
                }
                pre = cur;
                cur = cur.next;
                idx++;
            }
            if (idx == index)
                addAtTail(val);
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            ListNode cur = head.next;
            ListNode pre = head;
            int idx = 0;
            while (cur != null) {
                if (idx == index) {
                    pre.next = cur.next;
                }
                pre = cur;
                cur = cur.next;
                idx++;
            }
        }
    }


}
