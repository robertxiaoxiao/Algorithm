package LeetCode.UsefulDS;/*
 * @author: Robert
 * @date:  2020/2/19/019
 * @description:
 */


import java.util.*;

class AllOne {

    static class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.pre = head;
        }

        public void unlink(Node t) {
            t.pre.next = t.next;
            t.next.pre = t.pre;
        }

        // append left node after node right
        public void appendNode(Node left, Node right) {
            right.next = left.next;
            right.pre = left;
            left.next = right;
            right.next.pre = right;
        }
    }

    static class Node {
        int key;
        Node pre;
        Node next;
        Set<String> vals;

        public Node(int key) {
            this.key = key;
            vals = new HashSet<>();
        }
    }

    HashMap<String, Node> record;
    DoubleLinkedList ddl;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        record = new HashMap<>();
        ddl = new DoubleLinkedList();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (!record.containsKey(key)) {
            Node hd = ddl.head.next;
            if (hd.key == 1) {
                hd.vals.add(key);
                record.put(key, hd);
            } else {
                Node newnode = new Node(1);
                newnode.vals.add(key);
                ddl.appendNode(ddl.head, newnode);
                record.put(key, newnode);
            }
            return;
        }

        Node t = record.get(key);
        int cnt = t.key;
        if (t.next.key != cnt + 1) {
            Node newnode = new Node(cnt + 1);
            newnode.vals.add(key);
            t.vals.remove(key);
            ddl.appendNode(t, newnode);
            record.put(key, newnode);
            if (t.vals.size() == 0)
                ddl.unlink(t);
        } else {
            Node cur = t.next;
            cur.vals.add(key);
            record.put(key, cur);
            t.vals.remove(key);
            if (t.vals.size() == 0)
                ddl.unlink(t);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (!record.containsKey(key))
            return;

        Node t = record.get(key);
        record.remove(key);
        int cnt = t.key;
        if (cnt == 1) {
            t.vals.remove(key);
            if (t.vals.size() == 0)
                ddl.unlink(t);
        } else if (t.pre.key == cnt - 1) {
            Node cur = t.pre;
            cur.vals.add(key);
            record.put(key, cur);
            t.vals.remove(key);
            if (t.vals.size() == 0)
                ddl.unlink(t);
        } else {
            Node cur = t.pre;
            Node newnode = new Node(cnt - 1);
            newnode.vals.add(key);
            t.vals.remove(key);
            record.put(key, newnode);
            if (t.vals.size() == 0)
                ddl.unlink(t);
            ddl.appendNode(cur, newnode);
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        Set<String> set = ddl.tail.pre.vals;
        return set.isEmpty() ? "" : set.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        Set<String> set = ddl.head.next.vals;
        return set.isEmpty() ? "" : set.iterator().next();
    }

    public static void main(String[] args) {
        List<Node> t = new LinkedList<>();
        HashMap<Integer, Node> hm = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Node temp = new Node(i);
            t.add(temp);
            hm.put(i, temp);
        }
        for (int i = 0; i < 5; i++) {
            Node temp = hm.get(i);
            //  System.out.println(temp.key);
            t.remove(temp);
            System.out.println(t.size());
        }
    }

}