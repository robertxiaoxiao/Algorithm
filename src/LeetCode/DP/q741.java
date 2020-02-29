package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/3/003
 * @description:
 */

import java.util.HashMap;

public class q741 {

    static int beginstate;
    static int endstate;
    static int max = Integer.MIN_VALUE;
    int[][] fdir = {{0, 1}, {1, 0}};
    int[][] sdir = {{0, -1}, {-1, 0}};

    int getState(int x, int y, int base) {
        return x * base + y;
    }

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        beginstate = 0;
        endstate = (n - 1) * n + n - 1;
        int begincherry = grid[0][0];
        if (begincherry == 1)
            grid[0][0] = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        dfs(beginstate, begincherry, hm, grid, n, endstate, false);
        return max == Integer.MIN_VALUE ? -1 : max;
    }

    public void dfs(int state, int curcurry, HashMap<Integer, Integer> hm, int[][] grid, int base, int target, boolean arrived) {
        int x = state / base;
        int y = state % base;
        int n = grid.length;
        if (x * base + y == target && arrived) {
            if (curcurry > max)
                max = curcurry;
        }
        if (hm.containsKey(state)) {
            if (hm.get(state) < curcurry)
                return;
        }

        hm.put(state, curcurry);
        int[][] cur = arrived ? sdir : fdir;
        for (int i = 0; i < 2; i++) {
            int nx = x + cur[i][0];
            int ny = y + cur[i][1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || grid[nx][ny] == -1)
                continue;
            int nstate = getState(x, y, base);
            int nch = curcurry + grid[nx][ny];
            boolean change = false;
            if (grid[nx][ny] == 1) {
                grid[nx][ny] = 0;
                change = true;
            }
            if (nstate == endstate) {
                target = beginstate;
                arrived = true;
            }
            dfs(nstate, nch, hm, grid, base, target, arrived);
            if (change)
                grid[nx][ny] = 1;
        }
    }
}
