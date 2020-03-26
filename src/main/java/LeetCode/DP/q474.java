package LeetCode.DP;

public class q474 {

    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l + 1][m + 1][n + 1];
        for (int i = 1; i <= l; i++) {
            int[] oz = getOZ(strs[i - 1]);
            for (int j = 0; j <= m; j++)
                for (int k = 0; k <= n; k++) {

                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= oz[0] && k >= oz[1])
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - oz[0]][k - oz[1]] + 1);
                }

        }
        return dp[l][m][n];
    }

    public int[] getOZ(String s) {
        int zcnt = 0;
        int ocnt = 0;
        for (char c : s.toCharArray())
            if (c == '0')
                zcnt++;
            else
                ocnt++;
        return new int[]{zcnt, ocnt};
    }

}
