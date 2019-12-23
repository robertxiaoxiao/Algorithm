package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/22/022
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q818 {

    public int racecar(int target) {

        int[] mem = new int[target + 1];
        return memDp(target, mem);
    }

    static int Maxket = 10000;
    static int[][] dp = new int[Maxket + 1][2];

    public int racecarUsingDP(int target) {
        if (dp[target][0] == 0) {
            //  int[][] dp = new int[target + 1][2];
            dp[0][0] = 0;
            dp[0][1] = 1;
            for (int i = 1; i <= target; i++) {
                int n = (int) Math.ceil(Math.log(i + 1) / Math.log(2));
                if ((1 << n) == i + 1) {
                    dp[i][0] = n;
                    dp[i][1] = n + 1;
                    continue;
                }

                //  run over the node ,notice its original direction has been changed
                dp[i][0] = n + 1 + Math.min(dp[(1 << n) - 1 - i][0] + 1, dp[(1 << n) - 1 - i][1]);
                dp[i][1] = n + 1 + Math.min(dp[(1 << n) - 1 - i][0], dp[(1 << n) - 1 - i][1] + 1);

                //  traverse all subproblems
                for (int l = 1; l < i; l++)
                    for (int d = 0; d <= 1; d++) {
                        dp[i][d] = Math.min(dp[i][d], dp[l][0] + 2 + dp[i - l][d]);
                        dp[i][d] = Math.min(dp[i][d], dp[l][1] + 1 + dp[i - l][d]);
                    }
            }
        }

        return Math.min(dp[target][0], dp[target][1]);
    }

    public int memDp(int target, int[] mem) {
        if (mem[target] > 0)
            return mem[target];

        int n = (int) Math.ceil(Math.log(target + 1) / Math.log(2));

        // AAAAA n
        if (1 << n == target + 1) {
            mem[target] = n;
            return n;
        }

        // AAAA n+1 R
        int nexttarget = (1 << n) - 1 - target;
        int reverS = n + 1 + memDp(nexttarget, mem);
        mem[target] = reverS;

        //AAA n-1 r
        for (int m = 0; m < n - 1; m++) {
            // n-1+1+m+1
            int cur = (1 << (n - 1)) - (1 << m);
            mem[target] = Math.min(mem[target], n + m + 1 + memDp(target - cur, mem));
        }
        return mem[target];
    }

    // using bfs
    public int racecarUsingbfs(int target) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> hset = new HashSet<>();
        String start = "0_1";
        String startrev = "0_-1";
        queue.add(start);
        hset.add(start);
        // get rid of reverse at first step
        hset.add(startrev);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                String cur = queue.poll();

                String[] data = cur.split("_");
                int curpos = Integer.parseInt(data[0]);
                int curspeed = Integer.parseInt(data[1]);

                int nextApos = curpos + curspeed;
                int nextAspeed = curspeed * 2;
                if (nextApos == target)
                    return steps + 1;
                // brutal pruning
                if (Math.abs(nextAspeed) < 2 * target && nextApos < 2 * target) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(nextApos + "_" + nextAspeed);
                    String nextA = sb.toString();
                    if (!hset.contains(nextA)) {
                        hset.add(nextA);
                        queue.add(nextA);
                    }
                }

                int nextRpos = curpos;
                int nextRspeed = curspeed > 0 ? -1 : 1;
                String nextR = (new StringBuilder(nextRpos + "_" + nextRspeed)).toString();
                if (!hset.contains(nextR)) {
                    hset.add(nextR);
                    queue.add(nextR);
                }
            }
            ++steps;
        }
        return -1;
    }


}
