package LeetCode.DP;

import java.util.HashMap;

public class q873 {

    public int lenLongestFibSubseq(int[] A) {
        int n = A.length;
        int[][] dp = new int[n + 1][n + 1];
        HashMap<Integer, Integer> hm = new HashMap<>();
        int max = -1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[j][i] = 2;
                int pre = A[i - 1] - A[j - 1];
                if (pre < A[j - 1] && hm.containsKey(pre)) {
                    int k = hm.get(pre);
                    dp[j][i] = Math.max(dp[j][i], dp[k + 1][j] + 1);
                    max = Math.max(dp[j][i], max);
                }
            }
            hm.put(A[i - 1], i - 1);
        }
        return max >= 3 ? max : 0;
    }
}
