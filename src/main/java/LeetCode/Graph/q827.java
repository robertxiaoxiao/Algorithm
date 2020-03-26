package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/8/008
 * @description:
 */

import java.util.*;

public class q827 {

    static int[] dirs = {0, 1, 0, -1, 0};

    public int largestIslandUsingdye(int[][] grid) {
        int n = grid.length;
        if (n == 0)
            return 0;
        int m = grid[0].length;
        HashMap<Integer, Integer> colors = new HashMap<>();
        int color = 2;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    colors.put(color, dye(grid, i, j, color));
                    color++;
                }
            }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int temp = calcu(grid, i, j, colors) + 1;
                    if (temp > ans)
                        ans = temp;
                }
            }

        return ans == Integer.MIN_VALUE ? n * m : ans;
    }

    public int calcu(int[][] grid, int i, int j, HashMap<Integer, Integer> colors) {
        HashSet<Integer> visitedcolor = new HashSet<>();
        int cnt = 0;
        for (int k = 0; k < 4; k++) {
            int nx = i + dirs[k];
            int ny = j + dirs[k + 1];
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length||grid[nx][ny]==0)
                continue;
            int color = grid[nx][ny];
            if (visitedcolor.contains(color))
                continue;

            // zero
            cnt += colors.getOrDefault(color,0);
            visitedcolor.add(color);
        }
        return cnt;
    }

    public int dye(int[][] grid, int i, int j, int color) {
        int ans = 1;
        grid[i][j] = color;
        Queue<Integer> queue = new LinkedList<>();
        int start = i * base + j;
        queue.add(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int cx = cur / base;
            int cy = cur % base;
            for (int k = 0; k < 4; k++) {
                int nx = cx + dirs[k];
                int ny = cy + dirs[k + 1];
                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] != 1)
                    continue;
                int nstate = nx * base + ny;
                ++ans;
                grid[nx][ny] = color;
                queue.add(nstate);
            }
        }

        return ans;
    }

    public int largestIsland(int[][] grid) {
        int max = Integer.MIN_VALUE;
        int n = grid.length;
        if (n == 0)
            return 0;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] == 0)
                    max = Math.max(max, calc(grid, i, j, false, visited));
                else {
                    // speed the search jumping the visited node
                    if (!visited[i][j])
                        max = Math.max(max, calc(grid, i, j, true, visited));
                }
        return max;
    }

    static int base = 100;

    public int calc(int[][] grid, int i, int j, boolean check, boolean[][] visited) {
        int ans = 1;
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> seen = new HashSet<>();
        int start = i * base + j;
        queue.add(start);
        seen.add(start);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int cx = cur / base;
            int cy = cur % base;
            for (int k = 0; k < 4; k++) {
                int nx = cx + dirs[k];
                int ny = cy + dirs[k + 1];
                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] == 0)
                    continue;

                int nstate = nx * base + ny;
                if (seen.contains(nstate))
                    continue;
                seen.add(nstate);
                ++ans;
                queue.add(nstate);
            }
        }

        if (check) {
            for (int state : seen) {
                int x = state / base;
                int y = state % base;
                visited[x][y] = true;
            }
        }
        return ans;
    }


}
