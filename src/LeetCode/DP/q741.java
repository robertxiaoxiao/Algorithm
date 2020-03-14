package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/3/003
 * @description:
 */

import java.util.Arrays;

public class q741 {

    //    static int beginstate;
//    static int endstate;
//    static int max = Integer.MIN_VALUE;
//    int[][] fdir = {{0, 1}, {1, 0}};
//    int[][] sdir = {{0, -1}, {-1, 0}};
//
//    int getState(int x, int y, int base) {
//        return x * base + y;
//    }
//
//    public int cherryPickup(int[][] grid) {
//        int n = grid.length;
//        beginstate = 0;
//        endstate = (n - 1) * n + n - 1;
//        int begincherry = grid[0][0];
//        if (begincherry == 1)
//            grid[0][0] = 0;
//        HashMap<Integer, Integer> hm = new HashMap<>();
//        dfs(beginstate, begincherry, hm, grid, n, endstate, false);
//        return max == Integer.MIN_VALUE ? -1 : max;
//    }
//
//    public void dfs(int state, int curcurry, HashMap<Integer, Integer> hm, int[][] grid, int base, int target, boolean arrived) {
//        int x = state / base;
//        int y = state % base;
//        int n = grid.length;
//        if (x * base + y == target && arrived) {
//            if (curcurry > max)
//                max = curcurry;
//        }
//        if (hm.containsKey(state)) {
//            if (hm.get(state) < curcurry)
//                return;
//        }
//        hm.put(state, curcurry);
//        int[][] cur = arrived ? sdir : fdir;
//        for (int i = 0; i < 2; i++) {
//            int nx = x + cur[i][0];
//            int ny = y + cur[i][1];
//            if (nx < 0 || nx >= n || ny < 0 || ny >= n || grid[nx][ny] == -1)
//                continue;
//            int nstate = getState(x, y, base);
//            int nch = curcurry + grid[nx][ny];
//            boolean change = false;
//            if (grid[nx][ny] == 1) {
//                grid[nx][ny] = 0;
//                change = true;
//            }
//            if (nstate == endstate) {
//                target = beginstate;
//                arrived = true;
//            }
//            dfs(nstate, nch, hm, grid, base, target, arrived);
//            if (change)
//                grid[nx][ny] = 1;
//        }
//    }
    static int[][][] dp;

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        // dp[i][j][k]: the max cheery collected  starting from a(i,j) a(k,m) to (0,0)
        dp = new int[50][50][50];
        for (int i = 0; i < 50; i++)
            for (int j = 0; j < 50; j++)
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);

        return Math.max(0, helper(n - 1, n - 1, n - 1, grid));
    }

    public int helper(int x1, int y1, int x2, int[][] grid) {
        int y2 = x1 + y1 - x2;

        if (x1 < 0 || x2 < 0 || y1 < 0 || y2 < 0)
            return -1;

        if (x1 == 0 && y1 == 0)
            return grid[0][0];

        if (dp[x1][y1][x2] != Integer.MIN_VALUE)
            return dp[x1][y1][x2];

        if (grid[x1][y1] < 0 || grid[x2][y2] < 0)
            return dp[x1][y1][x2] = -1;

        int ans = Math.max(Math.max(helper(x1 - 1, y1, x2 - 1, grid), helper(x1, y1 - 1, x2, grid)),
                Math.max(helper(x1 - 1, y1, x2, grid), helper(x1, y1 - 1, x2 - 1, grid)));

        if (ans < 0)
            return dp[x1][y1][x2] = -1;
        ans += grid[x1][y1];
        if (x1 != x2)
            ans += grid[x2][y2];
        return dp[x1][y1][x2] = ans;
    }

    /*
    public int cherryPickup(int[][] grid) {
    int N = grid.length, M = (N << 1) - 1;
    int[][] dp = new int[N][N];
    dp[0][0] = grid[0][0];

    for (int n = 1; n < M; n++) {
		for (int i = N - 1; i >= 0; i--) {
			for (int p = N - 1; p >= 0; p--) {
				int j = n - i, q = n - p;

				if (j < 0 || j >= N || q < 0 || q >= N || grid[i][j] < 0 || grid[p][q] < 0) {
                    dp[i][p] = -1;
                    continue;
                 }

				 if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
				 if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
				 if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);

				 if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0)
             }
		 }
    }

    return Math.max(dp[N - 1][N - 1], 0);
}
     */


}
