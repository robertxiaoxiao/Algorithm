package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

public class q1289 {

    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[0][i] = arr[0][i];
        for (int i = 1; i < n; i++)
            for (int j = 0; j < n; j++) {
                int temp = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k == j)
                        continue;
                    temp = Math.min(temp, dp[i - 1][k]);
                }
                dp[i][j] = temp + arr[i][j];
            }

        int min = Integer.MAX_VALUE;
        for (int num : dp[n - 1]) {
            System.out.println(num);
            if (num < min)
                min = num;
        }
        return min;

    }
}
