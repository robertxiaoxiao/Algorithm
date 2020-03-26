package leetcodeTest.Tricks;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

public class q363 {
    public int maxSumSubmatrix(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            dp[i][1] = dp[i - 1][0] + mat[i - 1][0];
        for (int j = 1; j <= m; j++)
            dp[1][j] = dp[0][j - 1] + mat[0][j - 1];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];

        int maxsum = Integer.MIN_VALUE;
        int wide = Math.min(n, m);
        int len = Math.max(n, m);
        int maxarea = 0;
        for (int w = 1; w <= wide; w++)
            for (int l = 1; l <= len; l++) {
                for (int i = 0; i + w - 1 < n; i++)
                    for (int j = 0; j + l - 1 < m; j++) {
                        int ti = i + w - 1;
                        int tj = j + l - 1;
                        int res = calclulate(i, ti, j, tj, dp);
                        if (res <= k && res > maxsum)
                            maxsum = res;
                    }

                for (int i = 0; i + l - 1 < n; i++)
                    for (int j = 0; j + w - 1 < m; j++) {
                        int ti = i + l - 1;
                        int tj = j + w - 1;
                        int res = calclulate(i, ti, j, tj, dp);
                        if (res <= k && res > maxsum)
                            maxsum = res;
                    }
            }
        return maxsum;
    }

    public int calclulate(int x1, int x2, int y1, int y2, int[][] dp) {
        x1++;
        x2++;
        y1++;
        y2++;
        return dp[x2][y2] + dp[x1 - 1][y1 - 1] - dp[x1 - 1][y2] - dp[x2][y1 - 1];
    }


}
