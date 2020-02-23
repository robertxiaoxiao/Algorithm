package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/2/22/022
 * @description:
 */

public class q91 {

    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0 || s.charAt(0) == '0')
            return 0;

        int[] dp = new int[n];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int x = s.charAt(i - 1) - '0';
            int y = s.charAt(i) - '0';
            int xy = x * 10 + y;
            if (y >= 1 && y <= 9)
                dp[i] = dp[i - 1];

            if (xy >= 10 && xy <= 26)
                dp[i] += (i == 1) ? 1 : dp[i - 2];
        }

        return dp[n - 1];
    }

}
