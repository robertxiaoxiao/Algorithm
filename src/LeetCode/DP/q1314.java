package LeetCode.DP;

public class q1314 {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = mat[0][0];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                int t1 = Math.max(0, j - k);
                int t2 = Math.min(m - 1, j + k);
                int t3 = Math.max(0, i - k);
                int t4 = Math.min(n - 1, i + k);
                t1++;
                t2++;
                t3++;
                t4++;
                ans[i][j] = dp[t4][t2] + dp[t3 - 1][t1 - 1] - dp[t3 - 1][t2] - dp[t4][t1 - 1];
            }
        return ans;
    }
}
