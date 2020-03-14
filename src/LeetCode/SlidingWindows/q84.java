package LeetCode.SlidingWindows;

import java.util.Stack;

public class q84 {

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

        public void build(int left, int right, int tr) {
            if (left == right) {
                tree[tr] = new Node(data[left]);
                return;
            }
            int mid = left + (right - left) / 2;
            build(left, mid, tr * 2 + 1);
            build(mid + 1, right, tr * 2 + 2);
            tree[tr] = new Node(tree[tr * 2 + 1].minval > tree[tr * 2 + 2].minval ? tree[tr * 2 + 2] : tree[tr * 2 + 1]);
        }

        public Node query(int left, int right) {
            return query(left, right, 0, length - 1, 0);
        }

        public Node query(int left, int right, int curl, int curr, int tr) {
            if (left == curl && right == curr)
                return tree[tr];
            //   if we want the idx ,we need to return  index ;
            //   return tree[tr];
            int mid = curl + (curr - curl) / 2;
            if (right <= mid)
                return query(left, right, curl, mid, tr * 2 + 1);
            else if (left > mid)
                return query(left, right, mid + 1, curr, tr * 2 + 2);
            else {
                //
                Node leftnode = query(left, mid, curl, mid, tr * 2 + 1);
                Node rightnode = query(mid + 1, right, mid + 1, curr, tr * 2 + 2);
                return leftnode.minval > rightnode.minval ? rightnode : leftnode;
                //  return Math.min(tree[query(left, mid, curl, mid, tr * 2 + 1)], tree[query(mid + 1, right, mid + 1, curr, tr * 2 + 2)]);
            }
        }
    }

    public static int largestRectangleAreaUsingSegmentTree(int[] heights) {
        if (heights.length == 0)
            return 0;
        SegmentTree tree = new SegmentTree(heights);
        return maxarea(heights, tree, 0, heights.length - 1);
    }

    public static int maxarea(int[] heights, SegmentTree tree, int left, int right) {
        if (left > right)
            return -1;
        if (left == right)
            return heights[left];
        Node mid = tree.query(left, right, 0, heights.length - 1, 0);
        int theight = heights[mid.idx];
        int inheight = theight * (right - left + 1);
        int leftarea = maxarea(heights, tree, left, mid.idx - 1);
        int rightarea = maxarea(heights, tree, mid.idx + 1, right);
        return Math.max(inheight, Math.max(leftarea, rightarea));
    }

    public static int largestRectangleArea(int[] heights) {

        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
                stack.pop();
            if (stack.isEmpty())
                left[i] = -1;
            else
                left[i] = stack.peek();
            stack.push(i);
        }

        while (!stack.isEmpty())
            stack.pop();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i])
                stack.pop();

            if (stack.isEmpty())
                right[i] = n;
            else
                right[i] = stack.peek();

            stack.push(i);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, heights[i] * (right[i] - left[i] - 1));
        }

        return max;
    }

}
