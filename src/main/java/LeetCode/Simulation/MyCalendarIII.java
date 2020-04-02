package LeetCode.Simulation;

import java.util.TreeMap;

class MyCalendarThree {
    static class Node {
        int l, r, m, cnt; // m is the cut off point
        Node left;
        Node right;

        public Node(int l, int r, int m, int cnt) {
            this.l = l;
            this.r = r;
            this.m = m;
            this.cnt = cnt;
        }
    }

    static Node troot;
    static int max;

    public static void addNode(Node root, int s, int e) {
        if (root.m != -1) {
            if (e <= root.m)
                addNode(root.left, s, e);
            else if (s >= root.m)
                addNode(root.right, s, e);
            else {
                addNode(root.left, s, root.m);
                addNode(root.right, root.m, e);
            }
            return;
        }

        if (root.l == s && root.r == e) {
            max = Math.max(max, ++root.cnt);
            return;
        } else if (root.l == s) {
            root.m = e;
            root.left = new Node(root.l, e, -1, root.cnt + 1);
            root.right = new Node(root.m, root.r, -1, root.cnt);
            max = Math.max(max, root.cnt + 1);
        } else if (root.r == e) {
            root.m = s;
            root.left = new Node(root.l, s, -1, root.cnt);
            root.right = new Node(root.m, root.r, -1, root.cnt + 1);
            max = Math.max(max, root.cnt + 1);
        } else {
            root.m = s;
            root.left = new Node(root.l, s, -1, root.cnt);
            root.right = new Node(root.m, root.r, -1, root.cnt);
            addNode(root.right, s, e);
        }
    }

    public MyCalendarThree() {
        troot = new Node(0, 100000000, -1, 0);
        max = 0;
    }

    public int book(int start, int end) {
        addNode(troot, start, end);
        return max;
    }

}


//    class MyCalendarThree {
//        TreeMap<Integer, Integer> record;
//
//        public MyCalendarThree() {
//            record = new TreeMap<>();
//        }
//
//        public int book(int start, int end) {
//            int ps = record.getOrDefault(start, 0);
//            int pe = record.getOrDefault(end, 0);
//            record.put(start, ps + 1);
//            record.put(end, pe - 1);
//            int max = -1;
//            int sum = 0;
//            for (int k : record.keySet()) {
//                sum += record.get(k);
//                if (sum >= max)
//                    max = sum;
//            }
//            return max;
//        }
//
//    }



