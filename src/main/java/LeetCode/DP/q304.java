package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

public class q304 {

    class NumMatrix {
        private int[][] dp;

        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            if (n == 0)
                return;
            int m = matrix[0].length;
            dp = new int[n + 1][m + 1];
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            row1++;
            row2++;
            col1++;
            col2++;
            return dp[row2][col2] + dp[row1 - 1][col1 - 1] - dp[row1 - 1][col2] - dp[row2][col1 - 1];
        }
    }
}
