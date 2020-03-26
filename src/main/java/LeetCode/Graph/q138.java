package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class q138 {
    static HashMap<Node, Node> hm = new HashMap<>();

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomListUsingrec(Node head) {
        if (head == null)
            return null;
        if (hm.containsKey(head))
            return hm.get(head);
        Node newnode = new Node(head.val);
        hm.put(head, newnode);
        newnode.next = copyRandomListUsingrec(head.next);
        newnode.random = copyRandomListUsingrec(head.random);
        return newnode;
    }

    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        HashMap<Node, Node> hm = new HashMap<>();
        Node temp = head;
        while (temp != null) {
            hm.put(temp, new Node(temp.val));
            temp = temp.next;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.next != null)
                hm.get(cur).next = hm.get(cur.next);
            if (cur.random != null)
                hm.get(cur).random = hm.get(cur.random);
            cur = cur.next;
        }
        return hm.get(head);
    }

    public Node copyRandomListUsingO1(Node head) {
        if (head == null)
            return null;

        Node temp = head;
        while (temp != null) {
            Node copy = new Node(temp.val);
            Node next = temp.next;
            temp.next = copy;
            copy.next = next;
            temp = next;
        }

        temp = head;
        while (temp!= null) {
            if(temp.random!=null)
                temp.next.random = temp.random.next;
            temp = temp.next.next;
        }

        //CUT FORM THE ORIGINAL LIST
        Node cur = head;
        Node dummy = new Node(0);
        Node copy = dummy;
        while(cur != null) {
            copy.next = cur.next;
            cur.next = cur.next.next;
            copy = copy.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
