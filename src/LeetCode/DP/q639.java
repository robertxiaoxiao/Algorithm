package LeetCode.DP;

import java.util.Arrays;

public class q639 {


    int Mod = (int) 1e9 + 5;

    public int numDecodings(String s) {

        int n = s.length();
        if (n == 0 || s.charAt(0) == '0')
            return 0;

        int[][] dp = new int[n][10];

        // 0-9
        if (s.charAt(0) == '*') {
            Arrays.fill(dp, 1);
            dp[0][0] = 0;
        } else
            dp[0][s.charAt(0) - '0'] = 1;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) != '*' && s.charAt(i) != '*') {
                int x = s.charAt(i - 1) - '0';
                int y = s.charAt(i) - '0';
                update(s, i, x, y, dp);
            } else if (s.charAt(i - 1) != '*') {
                int x = s.charAt(i - 1) - '0';
                for (int k = 1; k <= 9; k++) {
                    update(s, i, x, k, dp);
                }
            } else if (s.charAt(i) != '*') {
                int y = s.charAt(i) - '0';
                for (int k = 1; k <= 9; k++) {
                    update(s, i, k, y, dp);
                }
            } else {
                for (int j = 1; j <= 9; j++)
                    for (int k = 1; k <= 9; k++) {
                        update(s, i, j, k, dp);
                    }
            }
            System.out.println(dp[i]);
        }
        int ans = 0;
        if (s.charAt(n - 1) == '*')
            for (int i : dp[n - 1])
                ans = (ans + i % Mod) % Mod;
        else
            ans = dp[n - 1][s.charAt(n - 1) - '0'];
        return ans;
    }

    public void update(String s, int i, int x, int y, int[][] dp) {
        int xy = x * 10 + y;
        if (y >= 1 && y <= 9)
            dp[i][y] = (dp[i][y] + dp[i - 1][x] % Mod) % Mod;
        if (xy >= 10 && xy <= 26)
            if (i == 1)
                dp[i][y] = (dp[i][y] + 1) % Mod;
            else {
                if (s.charAt(i - 2) != '*')
                    dp[i][y] = (dp[i][y] + (dp[i - 2][s.charAt(i - 2) - '0'] % Mod)) % Mod;
                else {
                    for (int k = 1; k <= 9; k++)
                        dp[i][y] = (dp[i][y] + (dp[i - 2][k] % Mod)) % Mod;
                }
            }
    }
}
