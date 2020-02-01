package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

import java.util.HashSet;

public class q778 {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int max = -1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }
        int s = 0;
        int e = max + 1;
        while (s < e) {
            int mid = s + (e - s) / 2;
            HashSet<Integer> seen = new HashSet<>();
            seen.add(0);
            boolean[][] visited = new boolean[n][n];
            visited[0][0] = true;
            if (!dfs(grid, n, visited, 0, 0, mid))
                s = mid + 1;
            else
                e = mid;
        }
        return s;
    }

    static int[] dirs = {0, 1, 0, -1, 0};
//    public boolean dfs(int[][] grid, int n, boolean[][] visited, int cx, int cy, int most) {
//        if (cx == n - 1 && cy == n - 1) {
//            return true;
//        }
//        visited[nx][ny] = true;
//        for (int k = 0; k < 4; k++) {
//            int nx = cx + dirs[k];
//            int ny = cy + dirs[k + 1];
//            if (nx < 0 || nx >= n || ny < 0 ||visited[nx][ny] ||ny >= n || grid[nx][ny] > most)
//                return false;
//            if (dfs(grid, n, visited, nx, ny, most))
//                return true;
//        }
//        visited[cx][cy] = false;
//        return false;
//    }

    public boolean dfs(int[][] grid, int n, boolean[][] visited, int cx, int cy, int most) {

        if (cx < 0 || cx >= n || cy < 0 || cy >= n || visited[cx][cy] || grid[cx][cy] > most)
            return false;

        if (cx == n - 1 && cy == n - 1) {
            return true;
        }

        visited[cx][cy] = true;
        for (int k = 0; k < 4; k++) {
            int nx = cx + dirs[k];
            int ny = cy + dirs[k + 1];
            if (dfs(grid, n, visited, nx, ny, most))
                return true;
        }
        visited[cx][cy] = false;
        return false;
    }

}
