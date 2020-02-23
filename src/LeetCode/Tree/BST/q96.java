package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/21/021
 * @description:
 */

public class q96 {

    public int numTrees(int n) {
        if (n == 0)
            return 0;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            dp[i][i] = 1;

        for (int len = 2; len <= n; len++)
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    if (k == i)
                        sum += dp[i + 1][j];
                    else if (k == j)
                        sum += dp[i][j - 1];
                    else
                        sum += dp[i][k - 1] * dp[k + 1][j];
                }
                dp[i][j] = sum;
            }
        return dp[1][n];
    }

}
