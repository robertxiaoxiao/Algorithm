package LeetCode.UsefulDS;/*
 * @author: Robert
 * @date:  2020/2/20/020
 * @description:
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LRUCache {

    class Node {
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, Node> record;
    LinkedList<Node> used;
    int cap;

    public LRUCache(int capacity) {
        record = new HashMap<>();
        used = new LinkedList<>();
        cap = capacity;
    }

    public void update(Node t) {
        used.remove(t);
        used.addFirst(t);
    }

    public int get(int key) {
        if (!record.containsKey(key))
            return -1;
        update(record.get(key));
        return record.get(key).val;
    }

    public void put(int key, int value) {
        
        Node t = record.getOrDefault(key, null);
        if (t == null) {
            Node newnode = new Node(key, value);
            if (record.size() == cap) {
                record.remove(used.getLast().key);
                used.removeLast();
            }
            used.addFirst(newnode);
            record.put(key, newnode);
        } else {
            t.val = value;
            update(t);
        }

    }


}
