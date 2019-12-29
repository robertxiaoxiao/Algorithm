package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.List;

public class Q1301 {

    public static int mod = (int) 1e9 + 7;

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();
        int[][] dp = new int[n + 1][n + 1];
        int[][] path = new int[n + 1][n + 1];
        dp[n - 1][n - 1] = 0;
        path[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--) {
                if (board.get(i).charAt(j) == 'X' || board.get(i).charAt(j) == 'S')
                    continue;
                int m = Math.max(dp[i + 1][j + 1], Math.max(dp[i][j + 1], dp[i + 1][j]));
                dp[i][j] = m + (i + j == 0? 0 : board.get(i).charAt(j) - '0');
                if (dp[i + 1][j + 1] == m)
                    path[i][j] = (path[i][j] % mod + path[i + 1][j + 1]) % mod;
                if (dp[i + 1][j] == m)
                    path[i][j] = (path[i][j] % mod + path[i + 1][j]) % mod;
                if (dp[i][j + 1] == m)
                    path[i][j] = (path[i][j] % mod + path[i][j + 1]) % mod;
            }

        return path[0][0]==0?new int[]{0,0}:new int[]{
                dp[0][0] , path[0][0]
        };

    }

}
