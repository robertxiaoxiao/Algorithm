package leetcodeTest.Week9;/*
 * @author: Robert
 * @date:  2020/1/5/005
 * @description:
 */

import BasicDStructure.Tree.SegmentTree;

import java.util.LinkedList;
import java.util.List;

public class q2 {
    static class Node {
        int minval;
        int idx;

        public Node(int idx, int val) {
            this.idx = idx;
            this.minval = val;
        }

        public Node(Node temp) {
            if (temp != null) {
                this.idx = temp.idx;
                this.minval = temp.minval;
            }
        }
    }

    static class SegmentTree {
        int length;
        Node[] data;
        Node[] tree;

        public SegmentTree(int[] arr) {
            length = arr.length;
            // 2^(log2n+1) UsualKey =4;
            data = new Node[length];
            tree = new Node[4 * length];
            for (int i = 0; i < length; i++)
                data[i] = new Node(i, arr[i]);
            build(0, length - 1, 0);
        }

        private void build(int left, int right, int tr) {
            if (left == right) {
                tree[tr] = new Node(data[left]);
                return;
            }
            int mid = left + (right - left) / 2;
            build(left, mid, tr * 2 + 1);
            build(mid + 1, right, tr * 2 + 2);
            pushup(tr);
            //      tree[tr] = new Node(tree[tr * 2 + 1].minval > tree[tr * 2 + 2].minval ? tree[tr * 2 + 2] : tree[tr * 2 + 1]);
        }

        private void pushup(int tr) {
            tree[tr] = new Node(tr, tree[tr * 2 + 2].minval ^ tree[(tr * 2) + 1].minval);
        }

        private int query(int left, int right) {
            return query(left, right, 0, length - 1, 0);
        }

        /*
           return the object reference
         */
        private int query(int left, int right, int curl, int curr, int tr) {
            if (left == curl && right == curr)
                return tree[tr].minval;
            //   if we want the idx ,we need to return  index ;
            //   return tree[tr];
            int mid = curl + (curr - curl) / 2;

            if (right <= mid)
                return query(left, right, curl, mid, tr * 2 + 1);
            else if (left > mid)
                return query(left, right, mid + 1, curr, tr * 2 + 2);
            else {
                //
                int leftnode = query(left, mid, curl, mid, tr * 2 + 1);
                int rightnode = query(mid + 1, right, mid + 1, curr, tr * 2 + 2);
                return rightnode ^ leftnode;
                //  return Math.min(tree[query(left, mid, curl, mid, tr * 2 + 1)], tree[query(mid + 1, right, mid + 1, curr, tr * 2 + 2)]);
            }
        }
    }

    public int[] xorQueriesUsingSegmentTree(int[] arr, int[][] queries) {
        SegmentTree segmentTree = new SegmentTree(arr);
        int[] res = new int[queries.length];
        int i = 0;
        for (int[] query : queries)
            res[i++] = segmentTree.query(query[0], query[1]);
        return res;
    }

    public int[] xorQueries(int[] arr, int[][] queries) {

        int n = arr.length;
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++)
            prefix[i] = prefix[i - 1] ^ arr[i];

        int[] res = new int[queries.length];
        int t = 0;
        for (int[] query : queries) {
            int j = query[0];
            int k = query[1];
            res[t++] = prefix[k] ^ (j - 1 < 0 ? 0 : prefix[j - 1]);
        }
        return res;
    }
}
