package LeetCode.DP;

import java.util.Arrays;

public class q1027 {

    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        int h = 10000;
        int[][] dp = new int[n][2 * h + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], 1);
        int ans = -1;
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++) {
                int d = A[j] - A[i] + h;
                dp[i][d] = dp[j][d] + 1;
                ans = Math.max(ans, dp[i][d]);
            }
        return ans;
    }

}
