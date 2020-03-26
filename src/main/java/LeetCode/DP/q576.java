package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

import java.util.Arrays;

public class q576 {
    static int[] dirs = {0, 1, 0, -1, 0};
    static int mod = (int) 1e9 + 7;

    public int findPaths(int m, int n, int N, int i, int j) {

        long[][][] dp = new long[m][n][N + 1];
        dp[i][j][0] = 1;
        long cnt = 0;
        for (int steps = 1; steps <= N; steps++)
            for (int cx = 0; cx < m; cx++)
                for (int cy = 0; cy < n; cy++) {
                    long path = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = cx + dirs[d];
                        int ny = cy + dirs[d + 1];
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                            continue;
                        path += dp[nx][ny][steps - 1];
                    }
                    dp[cx][cy][steps] = path % mod;
                    int base = 0;
                    if (cx + 1 >= m)
                        base++;
                    if (cx - 1 < 0)
                        base++;
                    if (cy + 1 >= n)
                        base++;
                    if (cy - 1 < 0)
                        base++;

                    cnt = (cnt % mod + (dp[cx][cy][steps - 1] * base) % mod) % mod;
                }

        return (int) cnt % mod;
    }

    public int findPathsUsingRec(int m, int n, int N, int i, int j) {
        int[][][] mem = new int[m][n][N + 1];
        for (int ii = 0; ii < m; ii++)
            for (int jj = 0; jj < n; jj++)
                Arrays.fill(mem[ii][jj], -1);
        return helper(m, n, N, i, j, mem);
    }

    public int helper(int m, int n, int steps, int i, int j, int[][][] mem) {

        if (i < 0 || i >= m || j < 0 || j >= n)
            return 1;

        if (steps == 0)
            return 0;

        if (mem[i][j][steps] != -1)
            return mem[i][j][steps];
        int val = 0;
        for (int d = 0; d < 4; d++) {
            val += helper(m, n, steps - 1, i + dirs[d], j + dirs[d + 1], mem) % mod;
            val = val % mod;
        }

        return mem[i][j][steps] = val;
    }
}
