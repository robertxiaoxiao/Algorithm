package LeetCode.DP;

public class q650 {

    public int minSteps(int n) {
        //   public int minSteps(int n) {
        //        if (n == 1) return 0;
        //        for (int i = 2; i < n; i++)
        //            if (n % i == 0) return i + minSteps(n / i);
        //        return n;
        //    }
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = i - 1; j > 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i / j);
                    break;
                }
            }
        }
        return dp[n];

//        int t = n / 2;
//        int[][] dp = new int[n + 1][n + 1];
//        int min = Integer.MAX_VALUE;
//        dp[1][0] = 0;
//        dp[1][1] = 1;
//        dp[2][1] = 2;
//        for (int i = 2; i <= n; i++)
//            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
//
//        for (int i = 2; i <= n; i++) {
//            for (int k = 1; k < i; k++) {
//                // past
//                dp[i][k] = Math.min(dp[i][k], dp[i - k][k] + 1);
//                for (int tk = 0; tk <= i / 2; k++)
//                    // collect and copy
//                    dp[i][k] = Math.min(dp[i][k], dp[i / 2][tk] + 2);
//                if (i == n)
//                    min = Math.min(min, dp[i][k]);
//            }
//        }
//        return min;
    }
}
