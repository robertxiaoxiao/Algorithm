package LeetCode.DP.区间DP;

public class q1388 {

    public int maxSizeSlices(int[] slices) {

//        int[] eslices = new int[n * 2];
//        for (int i = 0; i < 2 * n; i++)
//            eslices[i] = slices[i % n];

        int n = slices.length;
        int k = n / 3;
        int nlen = 2 * n;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i < n; i++)
            for (int j = 1; j <= k; j++)
                dp[i][j] = Math.max(dp[(i - 1 + n) % n][j], dp[(i - 2 + n) % n][j - 1] + slices[i]);

        return dp[n][k];
    }

    public int houserober(int[] slices) {
        if (slices.length == 3) return Math.max(Math.max(slices[0], slices[1]), slices[2]);
        int[][] dp1 = new int[slices.length][slices.length / 3 + 1];
        int[][] dp2 = new int[slices.length][slices.length / 3 + 1];

        //consider slices 0 -> n-1
        dp1[1][1] = slices[0];
        for (int i = 2; i < slices.length; i++) {
            for (int j = 1; j <= slices.length / 3; j++) {
                dp1[i][j] = Math.max(dp1[i - 1][j], dp1[i - 2][j - 1] + slices[i - 1]);
            }
        }

        //consider slice 1 -> n
        dp2[1][1] = slices[1];
        for (int i = 2; i < slices.length; i++) {
            for (int j = 1; j <= slices.length / 3; j++) {
                dp2[i][j] = Math.max(dp2[i - 1][j], dp2[i - 2][j - 1] + slices[i]);
            }
        }
        return Math.max(dp1[slices.length - 1][slices.length / 3], dp2[slices.length - 1][slices.length / 3]);
    }


}
