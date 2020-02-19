package leetcodeTest.Week15;/*
 * @author: Robert
 * @date:  2020/2/16/016
 * @description:
 */

import java.util.concurrent.ForkJoinPool;

public class q1 {
    public int countNegatives(int[][] grid) {
        int cnt = 0;
        if (grid.length == 0)
            return cnt;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0)
                    cnt++;
            }
        return cnt;
    }
}
