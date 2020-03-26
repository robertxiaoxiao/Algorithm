package LeetCode.SlidingWindows;

import java.util.Stack;

public class q42 {

    public int trap(int[] height) {

        Stack<Integer> stack = new Stack<>();
        int n = height.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int pre = 0;
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int last = stack.pop();
                ans += (i - last - 1) * (height[last] - pre);
                pre = height[last];
            }

            if (!stack.isEmpty())
                ans += (i - stack.peek() - 1) * (height[i] - pre);
            stack.push(i);
        }
        return ans;
    }

}
