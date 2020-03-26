package LeetCode.DP;

public class q413 {

    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        boolean[][] dp = new boolean[n][n];
        int cnt = 0;

        for (int len = 3; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = (2 * A[j - 1] == A[j - 2] + A[j]);
                if (!dp[i][j])
                    continue;
                if (len > 3)
                    dp[i][j] &= dp[i][j - 1];
                if (dp[i][j])
                    cnt++;
            }
        return cnt;
    }

    public int numberOfArithmeticSlicesON(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int[] dp = new int[A.length];

        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2])
                dp[i] = dp[i - 1] + 1;
        }
        int sum = 0;
        for (int i : dp) {
            sum += i;
        }
        return sum;
    }


}
