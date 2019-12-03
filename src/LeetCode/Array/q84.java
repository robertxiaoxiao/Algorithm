package LeetCode.Array;/*
 * @author:
 * @date:  2019/12/2/002
 * @description:
 */

public class q84 {
    public int largestRectangleArea(int[] heights) {
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
}
