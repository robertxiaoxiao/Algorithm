package LeetCode.UsefulDS;/*
 * @author: Robert
 * @date:  2020/2/20/020
 * @description:
 */

import java.util.*;

public class LFUCache {

    class Node {
        int key;
        int val;
        int freq;
        //  int tick;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
            //      this.tick = tick;
        }
    }

    TreeSet<Node> cache;
    HashMap<Integer, Node> hm;
    int tick;
    int size;

    /*
       LogN
    */
//    public LFUCache(int capacity) {
//        hm = new HashMap<>();
//        size = capacity;
////        cache = new TreeSet<>(new Comparator<Node>() {
////            @Override
////            public int compare(Node o1, Node o2) {
////                if (o1.freq == o2.freq)
////                    return o1.tick - o2.tick;
////                return o1.freq - o2.freq;
////            }
////        });
//        tick = 0;
//    }

//    public int get(int key) {
//        if (!hm.containsKey(key))
//            return -1;
//        Node temp = hm.get(key);
//        touch(temp, temp.val);
//        return temp.val;
//    }
//
//    public void put(int key, int value) {
//        if (size == 0)
//            return;
//        tick++;
//        Node t = hm.getOrDefault(key, null);
//        if (t == null) {
//            Node newnode = new Node(key, value, tick);
//            if (hm.size() == size) {
//                Node temp = cache.first();
//                hm.remove(temp.key);
//                cache.remove(temp);
//            }
//            hm.put(key, newnode);
//            cache.add(newnode);
//        } else {
//            touch(t, value);
//        }
//    }
//
//    public void touch(Node t, int val) {
//        cache.remove(t);
//        t.val = val;
//        t.freq++;
//        t.tick = tick;
//        cache.add(t);
//    }

    HashMap<Integer, LinkedList<Node>> frequency;
    HashMap<Integer, Node> record;
    int minfre;
    //int size;

    public LFUCache(int capacity) {
        frequency = new HashMap<>();
        record = new HashMap<>();
        minfre = 0;
        size = capacity;
    }

    public int get(int key) {
        if (!record.containsKey(key))
            return -1;
        update(record.get(key));
        return record.get(key).val;
    }

    public void update(Node t) {
        int nf = t.freq + 1;
        List<Node> cur = frequency.get(t.freq);
        cur.remove(t);
        if (!frequency.containsKey(nf)) {
            frequency.put(nf, new LinkedList<>());
        }
        frequency.get(nf).addFirst(t);
        t.freq++;
        if (cur.size() == 0) {
            frequency.remove(nf - 1);
            if (minfre == nf - 1)
                minfre = nf;
        }
    }

    public void put(int key, int val) {
        if (size == 0)
            return;
        Node t = record.getOrDefault(key, null);
        if (t == null) {
            Node newnode = new Node(key, val);
            if (record.size() == size) {
                Node todel = frequency.get(minfre).getLast();
                frequency.get(minfre).removeLast();
                if (frequency.get(minfre).size() == 0)
                    frequency.remove(minfre);
                record.remove(todel.key);
            }

            if (!frequency.containsKey(1))
                frequency.put(1, new LinkedList<>());
            LinkedList<Node> cur = frequency.get(1);
            cur.addFirst(newnode);
            minfre = 1;
            record.put(key, newnode);
        } else {
            t.val = val;
            update(t);
        }
    }


}
