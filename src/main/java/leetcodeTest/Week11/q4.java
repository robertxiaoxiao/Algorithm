package leetcodeTest.Week11;/*
 * @author: Robert
 * @date:  2020/1/19/019
 * @description:
 */

import java.util.Arrays;

public class q4 {

    public int minTaps(int n, int[] ranges) {
        // dp[i][j][k]=  i - j using pre k taps min num
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            int left = Math.max(i - ranges[i], 0);
            int right = Math.min(i + ranges[i], n);
            for (int t = left; t < right; t++) {
                if (dp[t] == -1)
                    continue;
                if (dp[right] == -1 || dp[right] > dp[t] + 1)
                    dp[right] = dp[t] + 1;
            }
        }
        return dp[n];
    }


}
