package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class q935 {

    static int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {-1, 0, -1}};
    static int[][] dirs = {
            {1, 2}, {-1, 2}, {1, -2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}
    };

    static int mod = (int) 1e9 + 7;

    public int knightDialer(int N) {
        int cnt = 0;
        int[][] mem = new int[N][100];
        for (int i = 0; i < N; i++)
            Arrays.fill(mem[i], -1);
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++)
                if (nums[i][j] != -1) {
                    cnt += helper(N - 1, i, j, mem) % mod;
                    cnt %= mod;
                }

        return cnt;
    }

    public int helper(int curSteps, int i, int j, int[][] mem) {
        if (curSteps == 0)
            return 1;
        int curstate = i * 10 + j;
        if (mem[curSteps][curstate] != -1)
            return mem[curSteps][curstate];
        int cnt = 0;
        for (int next : getHops(curstate)) {
            cnt += helper(curSteps - 1, next / 10, next % 10, mem) % mod;
            cnt %= mod;
        }
        return mem[curSteps][curstate] = cnt;
    }

    public List<Integer> getHops(int curstate) {
        int x = curstate / 10;
        int y = curstate % 10;
        int rows = 4;
        int cols = 3;
        List<Integer> res = new LinkedList<>();
        for (int d = 0; d < 8; d++) {
            int nx = x + dirs[d][0];
            int ny = y + dirs[d][1];
            if (nx < 0 || nx >= rows || ny < 0 || ny >= cols)
                continue;
            if (nx == 3 && (ny == 0 || ny == 2))
                continue;
            res.add(nx * 10 + ny);
        }
        return res;
    }
}
