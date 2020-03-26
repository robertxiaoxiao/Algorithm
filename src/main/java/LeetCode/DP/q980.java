package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

public class q980 {

    static int ans = 0;
    static int[] dirs = {0, 1, 0, -1, 0};

    public int uniquePathsIII(int[][] grid) {
        ans = 0;
        int znums = calculate(grid);
        int n = grid.length;
        int m = grid[0].length;
        int tx = 0, ty = 0, bx = 0, by = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    bx = i;
                    by = j;
                }
                if (grid[i][j] == 2) {
                    tx = i;
                    ty = j;
                }
            }
        visited[bx][by] = true;
        dfs(znums, 1, bx, by, tx, ty, grid, visited);
        return ans;
    }

    public void dfs(int tsum, int cur, int cx, int cy, int tx, int ty, int[][] grid, boolean[][] visited) {
        if (cx == tx && cy == ty) {
            if (tsum == cur)
                ans += 1;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = cx + dirs[i];
            int ny = cy + dirs[i + 1];
            if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] == -1)
                continue;
            if (visited[nx][ny])
                continue;
            visited[nx][ny] = true;
            dfs(tsum, cur + 1, nx, ny, tx, ty, grid, visited);
            visited[nx][ny] = false;
        }
    }

    private int calculate(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int num = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] != -1)
                    num++;
        return num;

    }

}
