package leetcodeTest.Week6;/*
 * @author: Robert
 * @date:  2019/12/15/015
 * @description:
 */

import java.util.*;

public class q4 {
    static class state {
        int x;
        int y;
        int power;
        int cursteps;

        public state(int x, int y, int power, int cursteps) {
            this.x = x;
            this.y = y;
            this.power = power;
        }
    }

    public int shortestPath(int[][] grid, int k) {
        //  int minsteps = Integer.MAX_VALUE;
        int n = grid.length;
        int m = grid[0].length;
        int[] dirs = {0, 1, 0, -1, 0};
        state start = new state(0, 0, k,0);
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
                int curk = curstate.power;

                for (int i = 0; i <4; i++) {
                    int nextx = curx + dirs[i];
                    int nexty = cury + dirs[i + 1];
                    if (nextx < 0 || nextx >=n || nexty < 0 || nexty >=m)
                        continue;
                    state newstate = new state(nextx, nexty, curk,minsteps+1);
                    // no power to eliminate
                    if (grid[nextx][nexty] == 1 && curk == 0)
                        continue;

                    if (grid[nextx][nexty] == 1)
                        newstate.power -= 1;

                    queue.add(newstate);
                }
            }
            ++minsteps;
        }
        return -1;
    }


}
