package LeetCode.UsefulDS;

import java.util.Stack;

public class MonitonicStack {

    Stack<Integer> stack;

    // when pushing value ,return the left closest less value
    public int push(int x) {
        while (!stack.isEmpty() && stack.peek() >= x)
            stack.pop();

        // the first left less than current value
        int min = -1;
        if (!stack.isEmpty())
            min = stack.peek();
        stack.push(x);
        return min;
    }

    public int getmin() {
        if (stack.isEmpty())
            return -1;
        return stack.peek();
    }

    public int[] getLeftMins(int[] nums) {
        int n = nums.length;
        int[] leftmins = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            push(nums[i]);
            leftmins[cnt++] = getmin();
        }
        return leftmins;
    }

}
