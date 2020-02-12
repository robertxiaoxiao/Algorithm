package leetcodeTest.Week14;/*
 * @author: Robert
 * @date:  2020/2/9/009
 * @description:
 */

import jdk.nashorn.api.tree.ForInLoopTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q4 {

    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        //dp [i][j]: 前i行 状态为j时最大数量
        int[][] dp = new int[m + 1][1 << 8];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);
        dp[0][0] = 0;
        int lim = (1 << n);
        for (int i = 1; i <= m; i++) {
            for (int k = 0; k < lim; k++) {
                for (int pre = 0; pre < lim; pre++) {
                    boolean flag = true;
                    if (dp[i - 1][pre] == -1)
                        continue;
                    for (int t = 0; t < n && flag; t++) {
                        if ((k & (1 << t)) == 0)
                            continue;
                        if (seats[i - 1][t] == '#')
                            flag = false;

                        int cur = t;
                        if (cur + 1 < n && (pre & (1 << (cur + 1))) != 0)
                            flag = false;

                        if (cur - 1 >= 0 && (pre & (1 << (cur - 1))) != 0)
                            flag = false;

                        if (cur + 1 < n && (k & (1 << (cur + 1))) != 0)
                            flag = false;

                        if (cur - 1 >= 0 && (k & (1 << (cur - 1))) != 0)
                            flag = false;
                    }
                    if (flag)
                        dp[i][k] = Math.max(dp[i][k], dp[i - 1][pre] + count(k));
                }
            }
        }

        int max = 0;
        for (int i = 0; i < lim; i++)
            max = Math.max(dp[m][i], max);
        return max;
    }

    public int count(int k) {
        int cnt = 0;
        while (k != 0) {
            if (k % 2 == 1)
                cnt++;
            k = k >> 1;
        }

        System.out.println(cnt);
        return cnt;
    }
}
