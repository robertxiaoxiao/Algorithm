package LeetCode.DP;

public class q718 {

    public int findLength(int[] A, int[] B) {
        int na = A.length;
        int nb = B.length;
        int[][] dp = new int[na + 1][nb + 1];
        int max = -1;
        for (int i = 1; i <= na; i++)
            for (int j = 1; j <= nb; j++) {
                //dp[i][j] : end with j and i subarray len
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = 0;
                max = Math.max(max, dp[i][j]);
            }
        return max;
    }


}
