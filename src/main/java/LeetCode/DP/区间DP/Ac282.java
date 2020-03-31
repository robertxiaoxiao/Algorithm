package LeetCode.DP.区间DP;

import java.util.Scanner;

public class Ac282 {

    static int N = 310;
    static int[][] dp;

    public static void main(String[] args) {
        dp = new int[N][N];
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(solve(arr));
    }

    public static int solve(int[] arr) {
        int n = arr.length;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            presum[i] = arr[i - 1] + presum[i - 1];

        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);

                dp[i][j] += presum[j + 1] - presum[i];

            }
        return dp[0][n - 1];
    }
}
