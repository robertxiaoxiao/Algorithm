package leetcodeTest.Week3;/*
 * @author:
 * @date:  2019/11/25/025
 * @description:
 */

public class q4 {
    //dp[i][j]   i steps  j state
    static int mod = (int) (Math.pow(10, 9) + 7);

/*
We basically need arrays of array to track the dp states (count) for each step. However the dp states for step i+1
only relates to dp states for step i. As a result, when it comes to step i+1, we can reuse the array for i-1 to track
 new dp states, which means we only need two arrays and use
 i%2 to determine which array is for current step and another one (i-1) %2 would be the previous step's states.
 */
    public int numWaysAc(int steps, int arrLen) {
        int[] dp1 = new int[arrLen + 2], dp2 = new int[arrLen + 2];
        dp1[1] = 1;
        for (int step = 0; step < steps; step++) {
            for (int i = 1; i <= arrLen; i++) {
                dp2[i] = (dp1[i - 1] + dp1[i]) % mod;
                dp2[i] = (dp2[i] + dp1[i + 1]) % mod;
                // judge bn dao
                if (dp2[i] == 0) {
                    break;
                }
            }
            int[] tmp = dp2;
            dp2 = dp1;
            dp1 = tmp;
        }
        return dp1[1];
    }

    public static int numWays(int steps, int arrLen) {
        int len = Math.min(steps + 1, arrLen);
        int[][] dp = new int[501][501];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++)
            for (int j = 0; j < len; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j > 0)
                    dp[i][j] = (dp[i][j]+dp[i - 1][j - 1]) % mod;
                if (j < len - 1)
                    dp[i][j] = (dp[i][j]+dp[i - 1][j + 1]) % mod;
            }
        return dp[steps][0];
    }
    public static void main(String[] args) {
        int i=1;

        System.out.println(i+=3%2);
        System.out.println(i);


    }
}
