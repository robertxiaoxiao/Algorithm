package LeetCode.DP.单调队列优化;

import java.util.Comparator;
import java.util.PriorityQueue;


public class q1425 {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o2[0] - o1[0];
                return o2[1] - o1[1];
            }
        });
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {

            dp[i] = nums[i];
            while (!pq.isEmpty() && i - pq.peek()[1] > k)
                pq.poll();

            if (!pq.isEmpty())
                dp[i] = Math.max(dp[i], dp[i] + pq.peek()[0]);

            pq.add(new int[]{dp[i], i});
            max = Math.max(max, dp[i]);
        }

        return max;
    }

}
