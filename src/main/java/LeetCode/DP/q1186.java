package LeetCode.DP;

public class q1186 {

    public int maximumSum(int[] arr) {
        int n = arr.length;
        if (n == 1)
            return arr[0];
        int[][] dp = new int[n][2]; // 0 no delete 1 operation
        for (int i = 0; i < n; i++)
            dp[i][0] = arr[i];
        int max = Integer.MIN_VALUE;
        for (int len = 2; len <= n; len++)
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][1] = Integer.MIN_VALUE;
                dp[i][0] = dp[i][0] + arr[j];
                int sum = dp[i][0];
                for (int k = i; k <= j; k++) {
                    dp[i][1] = Math.max(dp[i][1], sum - arr[k]);
                }
                max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
            }
        return max;
    }

    public int maximumSumIntution(int[] a) {
        int n = a.length;
        int[] maxEndHere = new int[n], maxStartHere = new int[n];
        int max = a[0];
        maxEndHere[0] = a[0];
        for (int i = 1; i < n; i++) {
            maxEndHere[i] = Math.max(a[i], maxEndHere[i - 1] + a[i]);
            max = Math.max(max, maxEndHere[i]);
        }
        maxStartHere[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--)
            maxStartHere[i] = Math.max(a[i], maxStartHere[i + 1] + a[i]);

        for (int i = 1; i < n - 1; i++)
            max = Math.max(max, maxEndHere[i - 1] + maxStartHere[i + 1]);
        return max;
    }


}
