package LeetCode.Array;/*
 * @author:
 * @date:  2019/12/2/002
 * @description:
 */


import java.util.Stack;

public class q84 {

    public static int largestRectangleAreaUsingOn(int[] heights) {

        int n = heights.length;
        int tp = 0;
        int i = 0;
        int maxarea = 0;
        int area_with_top = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < n) {
            if (stack.isEmpty() || heights[i] >= heights[tp])
                stack.push(i++);
            else {
                tp = stack.peek();
                //   stack stores the index of less than top ,the push index is the right index of smaller one ;
                // when conputed,it will pop from stack ;
                stack.pop();
                // height[i] >heights[top] ;
                //  4    6   5    tp=6 ;
                area_with_top = heights[tp] * (stack.isEmpty() ? i : i - stack.peek() - 1);

                if (area_with_top > maxarea)
                    maxarea = area_with_top;
            }
        }
        // the right smaller one is out of range  i=n;
        while (!stack.isEmpty()) {
            tp = stack.peek();
            stack.pop();
            area_with_top = heights[tp] * (stack.isEmpty() ? i : i - stack.peek() - 1);

            if (area_with_top > maxarea)
                maxarea = area_with_top;
        }
        return maxarea;
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
        int[] arr = {2, 3, 7, 5, 4, 8, 1};
        System.out.println(largestRectangleArea(arr));
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
