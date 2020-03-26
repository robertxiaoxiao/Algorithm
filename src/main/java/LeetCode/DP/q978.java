package LeetCode.DP;

public class q978 {

    public int maxTurbulenceSize(int[] A) {
        int n = A.length;
        int[] dp= new int[n];
        dp[0] = 1;
        int max = -1;
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1 && A[i] > A[i - 1])
                dp[i] = dp[i - 1] + 1;
            else if (i % 2 == 0 && A[i] < A[i - 1])
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = 1;
            max = Math.max(max, dp[i]);
        }

        for (int i = 1; i < n; i++) {
            if (i % 2 == 1 && A[i] < A[i - 1])
                dp[i] = dp[i - 1] + 1;
            else if (i % 2 == 0 && A[i] > A[i - 1])
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = 1;
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
