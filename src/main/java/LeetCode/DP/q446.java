package LeetCode.DP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q446 {

    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        int[][] dp = new int[n + 1][n + 1];
        int sum = 0;
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    if (len == 2) {
                        dp[i][j] = 1;
                    } else {
                        for (int t = 1; t < j; t++) {
                            //NOTICE THERE
                            long p1 = (long) A[i - 1] + (long) A[t - 1];
                            long p2 = (long) A[j - 1] + (long) A[j - 1];
                            if (p1 == p2)
                                dp[i][j] = dp[j][t];

                            sum += dp[i][j];
                        }
                    }
                }
            }
        }

        return sum;
    }

    public int numberOfArithmeticSlicesn2(int[] A) {
        int res = 0;
        Map<Integer, Integer>[] map = new Map[A.length];

        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>(i);

            for (int j = 0; j < i; j++) {
                long diff = (long) A[i] - A[j];
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;

                int d = (int) diff;
                int c1 = map[i].getOrDefault(d, 0);
                int c2 = map[j].getOrDefault(d, 0);
                res += c2;
                map[i].put(d, c1 + c2 + 1);
            }
        }

        return res;
    }


    public int numberOfArithmeticSlicesQuick(int[] A) {
        int n = A.length;
        int ans = 0;
        int[][] dp = new int[n][n];
        Map<Long, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey((long) A[i])) {
                map.put((long) A[i], new ArrayList<>());
            }
            map.get((long) A[i]).add(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long target = 2 * (long) A[j] - A[i];
                if (map.containsKey(target)) {
                    for (int k : map.get(target)) {
                        if (k < j) {
                            dp[i][j] += (dp[j][k] + 1);
                        }
                    }
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }

}
