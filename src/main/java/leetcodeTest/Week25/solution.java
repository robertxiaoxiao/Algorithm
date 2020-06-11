package leetcodeTest.Week25;

import java.lang.reflect.Array;
import java.util.*;

public class solution {

    public int maxScore(String s) {

        int n = s.length();
        int[] left = new int[n + 2];
        int[] right = new int[n + 2];
        left[0] = 0;
        right[n + 1] = 0;
        for (int i = 1; i < n; i++)
            left[i] = left[i - 1] + s.charAt(i - 1) == '0' ? 1 : 0;
        for (int i = n - 1; i >= 1; i--)
            right[i] = right[i + 1] + s.charAt(i - 1) == '1' ? 1 : 0;
        int max = 0;
        for (int i = 1; i <= n; i++)
            max = Math.max(max, left[i] + right[i + 1]);
        return max;
    }


    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] pre = new int[n + 2];
        int[] suf = new int[n + 2];
        pre[0] = 0;
        suf[n + 1] = 0;
        for (int i = 1; i <= n; i++)
            pre[i] = pre[i - 1] + cardPoints[i - 1];
        for (int i = n; i >= 1; i--)
            suf[i] = suf[i + 1] + cardPoints[i - 1];

        if (k == n)
            return pre[n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= k; i++)
            max = Math.max(max, pre[i] + suf[n - k + i + 1]);
        return max;
    }

    // DiagonalOrder : i+j==target  from bottom_left to top-right
    //                 i-j==target  from top-left to bottom-right
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();
        int m = nums.get(n - 1).size();

        int cnt = 0;
        int mmax = m;
        for (List<Integer> l : nums)
            mmax = Math.max(mmax, l.size());

        List<Integer>[] lists = new List[n + mmax];
        for (int i = n - 1; i >= 0; i--)
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (lists[i + j] == null)
                    lists[i + j] = new LinkedList<>();
                lists[i + j].add(nums.get(i).get(j));
                cnt++;
            }

        int[] ans = new int[cnt];
        int idx = 0;
        for (List<Integer> l : lists)
            if (l != null)
                for (int i : l)
                    ans[idx++] = i;
        return ans;
    }


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
