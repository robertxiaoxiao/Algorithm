package leetcodeTest.Week12;/*
 * @author: Robert
 * @date:  2020/1/26/026
 * @description:
 */

import java.util.Arrays;

public class q4 {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d)
            return -1;

        // dp[i][j] : min difficult of finishing the first i jobs in j day
        // dp[i][j] =dp[i-1][k]+max[jd[k+1,j]]

        int[][] dp = new int[d + 1][n + 1];

        for (int i = 0; i <= d; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        dp[0][0] = 0;

        for (int i = 1; i <= d; i++)
            for (int j = 1; j <= n; j++)
                for (int cut = i - 1; cut < j; cut++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][cut] + getMax(jobDifficulty, cut, j));
                }

        return dp[d][n];
    }

    public int getMax(int[] jobDifficulty, int str, int end) {
        int i = str;
        int ans = -1;
        while (i < end) {
            if (jobDifficulty[i] > ans)
                ans = jobDifficulty[i];
            i++;
        }
        return ans;
    }
}
