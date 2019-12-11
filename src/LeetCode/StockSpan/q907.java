package LeetCode.StockSpan;/*
 * @author: Robert
 * @date:  2019/12/11/011
 * @description:
 */


public class q907 {
    static int mod = (int) 1e9 + 7;

    static class SegmentTree {

        int[] data;
        int[] tree;

        public SegmentTree(int[] A) {
            int n = A.length;
            data = new int[n];
            tree = new int[4 * n];
            for (int i = 0; i < n; i++)
                data[i] = A[i];

            buildTree(0, 0, n - 1);
        }

        public void buildTree(int tnode, int left, int right) {
            if (left == left) {
                tree[tnode] = data[left];
                return;
            }
            int leftchild = getLeft(tnode);
            int rightchild = getRight(tnode);
            int mid = left + (right - left) / 2;
            buildTree(leftchild, left, mid);
            buildTree(rightchild, mid + 1, right);
            tree[tnode] = Math.min(tree[leftchild], tree[rightchild]);
        }

        public int getLeft(int tnode) {
            return tnode * 2 + 1;
        }

        public int getRight(int tnode) {
            return tnode * 2 + 2;
        }

        public int query(int left, int right) {
            return query(0, left, right, 0, data.length - 1);
        }

        public int query(int tnode, int curl, int curr, int left, int right) {
            if (curl == left && curr == right)
                return tree[tnode];
            int mid = left + (right - left) / 2;
            if (curl > mid)
                return query(getRight(tnode), curl, curr, mid + 1, right);
            if (curr <= mid)
                return query(getLeft(tnode), curl, curr, left, mid);

            return Math.min(query(getRight(tnode), mid + 1, curr, mid + 1, right), query(getLeft(tnode), curl, mid, left, mid));
        }
    }

    public int sumSubarrayMins(int[] A) {
        SegmentTree segmentTree = new SegmentTree(A);
        int size = segmentTree.tree.length;
        System.out.println(size);
        int sum = 0;
        for (int i = 0; i < size; i++) {
            System.out.println(segmentTree.tree[i]);
            sum = sum + segmentTree.tree[i] % mod;
        }
        return -1;
    }

}
