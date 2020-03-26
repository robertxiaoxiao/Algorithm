package LeetCode.Array;/*
 * @author:
 * @date:  2019/12/2/002
 * @description:
 */


import java.util.Stack;

public class q84 {

    /*
      q85  max rectangle
      q85  max area in histogram
     */
    public int maximalRectangle(char[][] matrix) {

        int n = matrix.length;
        int m = matrix[0].length;
        int[] height = new int[m];
        int maxarea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1')
                    height[j]++;
                else
                    height[j] = 0;
            }
            maxarea = Math.max(maxarea, largestRectangleAreaUsingStack(height));
        }
        return maxarea;
    }

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

    public static int largestRectangleAreaUsingStack(int[] heights) {
        int n = heights.length;
        int tp;
        int i = 0;
        int maxarea = 0;
        int area_with_top;
        Stack<Integer> stack = new Stack<>();
        while (i < n) {
            if (stack.isEmpty())
                stack.push(i++);
            else {
                tp = stack.peek();
                if (heights[i] >= heights[tp])
                    stack.push(i++);
                else {
                    // stack stores the index of less than top ,the push index is the right index of smaller one ;
                    // when computed,it will pop from stack ;
                    stack.pop();
                    // height[i] >heights[top] ;
                    //  4    6   5    tp=6 ;
                    int lessfromleft = -1;
                    if (!stack.isEmpty())
                        lessfromleft = stack.peek();
                    area_with_top = heights[tp] * (i - lessfromleft - 1);
                    if (area_with_top > maxarea)
                        maxarea = area_with_top;
                }
            }
        }

        // if all the same ,it will calculated from end;
        // the right smaller one is out of range  i=n;
        while (!stack.isEmpty()) {
            tp = stack.peek();
            stack.pop();
            int lessfromleft = -1;
            if (!stack.isEmpty())
                lessfromleft = stack.peek();
            area_with_top = heights[tp] * (i - lessfromleft - 1);
            if (area_with_top > maxarea)
                maxarea = area_with_top;
        }
        return maxarea;
    }

    public int largestRectangleAreaSimply(int[] heights) {
        int n = heights.length;
        if (n == 0)
            return 0;
        int area = Math.max(heights[0], heights[n - 1]);
        int[] lessfromleft = new int[n];
        int[] lessfromright = new int[n];

        for (int i = 1; i < n; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i])
                p--;
            lessfromleft[i] = p + 1;
        }
        lessfromleft[0] = 0;
        lessfromright[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int t = i + 1;
            while (t <= n - 1 && heights[t] >= heights[i])
                t++;
            lessfromright[i] = t - 1;
        }

        for (int i = 0; i < n; i++)
            area = Math.max(area, heights[i] * (lessfromright[i] - lessfromleft[i] + 1));
        return area;
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0)
            return 0;
        int area = Math.max(heights[0], heights[n - 1]);
        int[] lessfromleft = new int[n];
        int[] lessfromright = new int[n];
        lessfromleft[0] = 0;
        callessfromleft(lessfromleft, heights);
        System.out.println();
        callessfromleftI(lessfromleft, heights);
        //        for (int i = 1; i < n; i++) {
//            int p = i - 1;
//            while (p >= 0 && heights[p] >= heights[i])
//                   p=lessfromleft[p];
//                   //p--;
//            lessfromleft[i] = p + 1;
//        }

        lessfromright[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            int t = i + 1;
            while (t <= n - 1 && heights[t] >= heights[i])
                t++;
            lessfromright[i] = t - 1;
        }
        for (int i = 0; i < n; i++)
            area = Math.max(area, heights[i] * (lessfromright[i] - lessfromleft[i] + 1));

        return area;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        SegmentTree tree = new SegmentTree(arr);
        // tree.build(0,arr.length,0);
//        System.out.println(tree.query(0, 2));
//        System.out.println(tree.query(0, 3));
//        System.out.println(tree.query(1, 2));
        System.out.println(largestRectangleAreaUsingSegmentTree(arr));
    }

    public static void callessfromleft(int[] lessfromleft, int[] heights) {

        int n = lessfromleft.length;
        for (int i = 1; i < n; i++) {
            int p = i - 1;
            // some trick  to decrease O(T)  from O(N^2)  to O(N) ;
            while (p >= 0 && heights[p] >= heights[i])
                p = lessfromleft[p] - 1;
            lessfromleft[i] = p + 1;
        }

        for (int i : lessfromleft)
            System.out.print(i + "    ");
    }

    public static void callessfromleftI(int[] lessfromleft, int[] heights) {

        int n = lessfromleft.length;
        for (int i = 1; i < n; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i])
                p--;
            lessfromleft[i] = p + 1;
        }
        for (int i : lessfromleft)
            System.out.print(i + "    ");
    }
}
