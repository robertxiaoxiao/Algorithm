package LeetCode.DP;

import java.util.HashSet;

public class q967 {


    public int[] numsSameConsecDiff(int N, int K) {

        HashSet<Integer> list = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        HashSet<Integer>[] dp = new HashSet[N + 1];
        dp[1] = list;

        for (int i = 2; i <= N; i++) {
            HashSet<Integer> cur = new HashSet<>();
            for (int n : dp[i - 1]) {
                int low = n % 10;
                // NOTICE THERE 
                if (n > 0 && low + K < 10)
                    cur.add(n * 10 + low + K);
                if (n > 0 && low - K >= 0)
                    cur.add(n * 10 + low - K);
            }
            dp[i] = cur;
        }

        int n = dp[N].size();
        int[] ans = new int[n];
        int idx = 0;
        for (int i : dp[N])
            ans[idx++] = i;
        return ans;
    }

    public static int[] getHL(int n, int k) {
        int[] arr = new int[2];
        arr[0] = n / (int) (Math.pow(10, k - 1));
        arr[1] = n % 10;
        return arr;
    }


}
