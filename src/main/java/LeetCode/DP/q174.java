package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

public class q174 {

    public int calculateMinimumHP(int[][] dungeon) {

        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = dungeon[n - 1][m - 1] > 0 ? 1 : 1 - dungeon[n - 1][m - 1];
        for (int i = n - 2; i >= 0; i--) {
            int temp = dp[i + 1][m - 1] - dungeon[i][m - 1];
            dp[i][m - 1] = temp > 0 ? temp : 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            int temp = dp[n - 1][i + 1] - dungeon[n - 1][i];
            dp[n - 1][i] = temp > 0 ? temp : 1;
        }
        //dp[i][j] =Math.min(dp[i+1][j]-dungeon[i+1][j],dp[i][j+1])-dungeon[i][j+1])
        for (int i = n - 2; i >= 0; i--)
            for (int j = m - 2; j >= 0; j--) {
                int temp = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = temp > 0 ? temp : 1;
            }
        return dp[0][0];
    }
}
