package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/11/011
 * @description:
 */

import javax.naming.spi.DirStateFactory;
import javax.swing.plaf.nimbus.State;

public class q174 {


    public int calculateMinimumHP(int[][] dungeon) {

        int n = dungeon.length;
        int m = dungeon[0].length;

        int sum = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                sum += dungeon[i][j] > 0 ? dungeon[i][j] : -dungeon[i][j];
            }

        int l = dungeon[0][0] >= 0 ? 1 : -dungeon[0][0];
        int r = Integer.MAX_VALUE / 2;
        while (l < r) {
            int mid = (l + r) / 2;
            int[][] dp = new int[n][m];
            if (!dfs(mid, 0, 0, dungeon, n, m, dp))
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }

    public boolean canslove(int start, int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    dp[0][0] = start + dungeon[0][0];
                else {
                    int prev_max = -1;
                    if (i > 0) {
                        prev_max = dp[i - 1][j];
                    }
                    if (j > 0) {
                        prev_max = Math.max(prev_max, dp[i][j - 1]);
                    }
                    if (prev_max <= 0) {
                        dp[i][j] = -1;
                    } else {
                        dp[i][j] = dungeon[i][j] + prev_max;
                    }
                    int a = Integer.MIN_VALUE;
                    int b = Integer.MIN_VALUE;
                    if (i - 1 >= 0 && dp[i - 1][j] > 0)
                        a = dp[i - 1][j] + dungeon[i][j];

                    if (j - 1 >= 0 && dp[i][j - 1] > 0)
                        b = dp[i][j - 1] + dungeon[i][j];

                    dp[i][j] = Math.max(a, b);
                }
            }
        return dp[n - 1][m - 1] > 0;
    }


    /* *
        unable
     */
    public boolean dfs(int inith, int cx, int cy, int[][] dungeon, int n, int m, int[][] dp) {
        if (inith<= 0) {
            dp[cx][cy] = -1;
            return false;
        }

        if (dp[cx][cy] != 0) {
            return dp[cx][cy] == 1;
        }

        if (cx == n - 1 && cy == m - 1 && inith + dungeon[cx][cy] > 0)
            return true;

        int[][] dirs = {{0, 1}, {1, 0}};

        for (int[] dir : dirs) {
            int nx = cx + dir[0];
            int ny = cy + dir[1];
            int nh = inith + dungeon[nx][ny];
            if (nx >= n || ny >= m)
                continue;
            if (dfs(nh, nx, ny, dungeon, n, m, dp)) {
                dp[cx][cy] = 1;
                return true;
            }
        }
        dp[cx][cy] = -1;
        return false;
    }

    public static void main(String[] args) {

        System.out.println(Integer.MIN_VALUE - 1);
    }
}
