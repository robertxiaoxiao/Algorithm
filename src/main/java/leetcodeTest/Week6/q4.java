package leetcodeTest.Week6;/*
 * @author: Robert
 * @date:  2019/12/15/015
 * @description:
 */

import java.util.*;

public class q4 {

    static int[] dirs = {0, -1, 0, 1, 0};

    static class state {
        int x;
        int y;
        int power;

        public state(int x, int y, int power) {
            this.x = x;
            this.y = y;
            this.power = power;
        }
    }

    static int[][] seen = new int[50][50];
    static int[][][] dist = new int[50][50][1600];

    public int shortestPathUsingDFS(int[][] grid, int k) {

        return -1;
    }

    public int dfs(int x, int y, int[][] grid, int k) {
        if (x > grid.length || y > grid[0].length || x < 0 || y < 0 || k < 0 || seen[x][y] == -1)
            return Integer.MAX_VALUE;

        if (x == 1 && y == 1)
            return 0;

        if (x == grid.length && y == grid[0].length)
            return dist[x][y][k];

        if (dist[x][y][k] != -1)
            return dist[x][y][k];

        int ans = Integer.MAX_VALUE;
        seen[x][y] = 1;
        for (int i = 0; i < 4; i++)
            ans = Math.min(ans, 1 + dfs(x + dirs[i], y + dirs[i + 1], grid, k - grid[x - 1][y - 1]));
        dist[x][y][k] = ans;
        seen[x][y] = 0;
        return ans;
    }

    public int shortestPathUsingDp(int[][] grid, int k) {
        /*
             dp[x][y][k]= the minSteps from (0,0)  to (x,y) in k obstacles
         */

        int[][][] dp = new int[40][40][k + 1];
        dp[0][0][0] = 0;
        int min = Integer.MAX_VALUE;
        for (int x = 1; x < grid.length; x++)
            for (int y = 0; y < grid.length; y++) {
                Arrays.fill(dp[x][y], Integer.MAX_VALUE);
                for (int m = 0; m <= k; m++) {

                    int minsteps = Integer.MAX_VALUE;
                    if (x - 1 >= 0)
                        minsteps = Math.min(dp[x - 1][y][k - grid[x - 1][y]], minsteps);
                    if (x + 1 < grid.length)
                        minsteps = Math.min(dp[x + 1][y][k - grid[x + 1][y]], minsteps);
                    if (y - 1 >= 0)
                        minsteps = Math.min(dp[x][y - 1][k - grid[x][y - 1]], minsteps);
                    if (y + 1 < grid.length)
                        minsteps = Math.min(dp[x][y + 1][k - grid[x][y + 1]], minsteps);

                    dp[x][y][m] = minsteps == Integer.MAX_VALUE ? minsteps : minsteps + 1;

                    if (x == grid.length - 1 && y == grid[0].length - 1)
                        min = Math.min(min, dp[x][y][m]);
                }
            }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int shortestPath(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        state start = new state(0, 0, 0);
        boolean[][] seen = new boolean[1600][k];
        seen[0][0] = true;
        // seen[x][y][k] : the k obstacles  in path from (0,0) to (x,y)
        // bfs : seen to check whether a node has been visited ;
        Queue<state> queue = new LinkedList<>();
        queue.add(start);
        int minsteps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                state curstate = queue.poll();
                int curx = curstate.x;
                int cury = curstate.y;
                if (curx == n - 1 && cury == m - 1)
                    return minsteps;

                for (int i = 0; i < 4; i++) {
                    int curk = curstate.power;
                    int nextx = curx + dirs[i];
                    int nexty = cury + dirs[i + 1];
                    if (nextx < 0 || nextx >= n || nexty < 0 || nexty >= m)
                        continue;
                    curk += grid[nextx][nexty];
                    if (curk > k || seen[nextx * 40 + nexty][curk])
                        continue;

                    seen[nextx * 40 + nexty][curk] = true;
                    queue.add(new state(nextx, nexty, curk));
                }
            }
            ++minsteps;
        }
        return -1;
    }


}
