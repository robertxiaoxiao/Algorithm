package RelatedImpl;/*
 * @author:
 * @date:  2019/11/26/026
 * @description:
 */

// code should be write line by line
/*

    segment tree node nums :
       if n=2^k , O(C)=2n
       if n=2^k+1 , O(C)=4n
 */


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class SegmentTree<E> {

    public interface Merger<E> {
        E merge(E a, E b);
    }

    public E[] data;
    public E[] tree;
    private Merger<E> merger;
    private Merger<E> deMerger;

    public SegmentTree(E[] arr, Merger<E> merger, Merger<E> deMerger) {
        this.merger = merger;
        this.deMerger = deMerger;
        int n = arr.length;
        /*
         genericity initial
          */
        data = (E[]) new Object[n];
        for (int i = 0; i < n; i++)
            data[i] = arr[i];
        tree = (E[]) new Object[n * 4];
        buildSegmentTree(0, 0, n - 1);
    }

    /*
           treeNode :  segment tree node root
           l :  section left end
           r :  section right end
           DFS tree[Node]=[l,l];
     */
    public void buildSegmentTree(int treeNode, int l, int r) {
        if (l == r) {
            tree[treeNode] = data[l];
            return;
        }
        int mid = l + (r - l) / 2;
        int leftchild = getleftChild(treeNode);
        int rightchild = getrightChild(treeNode);
        buildSegmentTree(leftchild, l, mid);
        buildSegmentTree(rightchild, mid + 1, r);
        tree[treeNode] = merger.merge(tree[leftchild], tree[rightchild]);
    }

    private int getleftChild(int treeNode) {
        return treeNode * 2 + 1;
    }

    private int getrightChild(int treeNode) {
        return treeNode * 2 + 2;
    }

    public E query(int l, int r) {
        return query(0, 0, data.length - 1, l, r);
    }

    public E query(int treeNode, int l, int r, int queryL, int queryR) {
        if (queryL == l && queryR == r)
            return tree[treeNode];

        int leftchild = getleftChild(treeNode);
        int rightchild = getrightChild(treeNode);
        int mid = l + (r - l) / 2;
        if (queryL > mid)
            return query(rightchild, mid + 1, r, queryL, queryR);

        if (queryR <= mid)
            return query(leftchild, l, mid, queryL, queryR);

        E leftResult = query(leftchild, l, mid, queryL, mid);
        E rightResult = query(rightchild, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    public void update(int targetNode, E val) {
        if (targetNode < 0 || targetNode > data.length)
            throw new IllegalArgumentException("target node out of range");
        data[targetNode] = val;
        update(0, targetNode, val, 0, data.length - 1);
    }

    public void update(int rootNode, int targetNode, E val, int l, int r) {

        if (l == r) {
            // targetNode in  treeNodeIndex
            tree[rootNode] = val;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftchild = getleftChild(rootNode);
        int rightchild = getrightChild(rootNode);

        if (targetNode <= mid)
            update(leftchild, targetNode, val, l, mid);
        else
            update(rightchild, targetNode, val, mid + 1, r);

        tree[rootNode] = merger.merge(tree[leftchild], tree[rightchild]);
    }

    public void remove(int targetNode) {
        // empty
        // update(targetNode,0);
        remove(0, targetNode, data[targetNode], 0, data.length - 1);

    }

    public void remove(int rootNode, int targetNode, E val, int l, int r) {
        if (l == r) {
            // targetNode in  treeNodeIndex
            tree[rootNode] = deMerger.merge(tree[rootNode], val);
            return;
        }
        int mid = l + (r - l) / 2;
        int leftchild = getleftChild(rootNode);
        int rightchild = getrightChild(rootNode);

        if (targetNode <= mid)
            remove(leftchild, targetNode, val, l, mid);
        else
            remove(rightchild, targetNode, val, mid + 1, r);
        tree[rootNode] = merger.merge(tree[leftchild], tree[rightchild]);
    }

    public void print() {
        int root = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(root);
        int preSize = 1;
        int curCol = 1;
        while (!queue.isEmpty()) {
            preSize = curCol;
            curCol = 0;
            while (preSize != 0) {
                int temp = queue.poll();
                preSize--;
                if (getleftChild(temp) < tree.length) {
                    queue.add(getleftChild(temp));
                    curCol += 1;
                }
                if (getrightChild(temp) < tree.length) {
                    queue.add(getrightChild(temp));
                    curCol += 1;
                }
                System.out.print(tree[temp] + "  ");
            }
            System.out.println();
        }
    }

    public static class Add implements Merger<Integer> {
        @Override
        public Integer merge(Integer a, Integer b) {
            return a + b;
        }
    }

    public static class deleter implements Merger<Integer> {
        @Override
        public Integer merge(Integer a, Integer b) {
            return a - b;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Merger adder = new Add();
        Merger deleter = new deleter();
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(arr, adder, deleter);
        segmentTree.print();
        int sum = 0;
        int l = 1, r = 6;
        for (int i = 1; i <= r; i++)
            sum += arr[i];
        System.out.printf("%d    %d\r\n", segmentTree.query(l, r), sum);
        segmentTree.update(0, 10);
        segmentTree.update(3, 10);
        segmentTree.print();
        segmentTree.remove(3);
        segmentTree.print();
    }
}