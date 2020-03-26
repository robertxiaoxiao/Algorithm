package LeetCode.SlidingWindows;

import java.util.Deque;
import java.util.LinkedList;

public class q239 {

    public int[] maxSlidingWindowUsingelements(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int n = nums.length;
        if (n == 0)
            return new int[0];

        int[] ans = new int[n - k + 1];
        int cnt = 0;
        // List<Integer> ans = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.getLast() < nums[i])
                deque.pollLast();
            deque.addLast(nums[i]);
            if (i - k + 1 >= 0) {
                int max = deque.getFirst();
                ans[cnt++] = max;
                // we need to pop the first element
                if (nums[i - k + 1] == max)
                    deque.pollFirst();
            }
        }
        return ans;
    }

    // using the idx in replace of the element
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();

        int n = nums.length;
        if (n == 0)
            return new int[0];

        int[] ans = new int[n - k + 1];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && i - k + 1 > deque.getFirst())
                deque.pollFirst();

            while (!deque.isEmpty() && nums[deque.getLast()] <= nums[i])
                deque.pollLast();

            deque.addLast(i);
            if (i - k + 1 >= 0)
                ans[cnt++] = nums[deque.getFirst()];
        }
        return ans;
    }

}
