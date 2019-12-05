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

    static class node {
        int left;
        int right;
        int minval;
    }

    static class SegmentTree {

    }

    public static int largestRectangleAreaUsingSegmentTree(int[] heights) {




    }


    public static int largestRectangleAreaUsingStack(int[] heights) {
        int n = heights.length;
        int tp = 0;
        int i = 0;
        int maxarea = 0;
        int area_with_top = 0;
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
        int[] arr = {2, 2, 2, 2};
        System.out.println(largestRectangleAreaUsingStack(arr));
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
