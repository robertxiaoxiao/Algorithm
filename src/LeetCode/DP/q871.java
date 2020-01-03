package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/2/002
 * @description:
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class q871 {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {

        int n = stations.length;
        // dp[n]  the maxlen of using the first n  stations
        int[] dp = new int[n + 1];
        dp[0] = startFuel;

        for (int i = 1; i <= n; i++)
            for (int j = i; j > 0; j--) {
                if (dp[j - 1] >= stations[i - 1][0])
                    dp[j] = Math.max(dp[j], dp[j - 1] + stations[i - 1][1]);
            }

        for (int i = 0; i <= n; i++)
            if (dp[i] >= target)
                return i;
        return -1;
    }

    public int simulate(int target, int startFuel, int[][] stations) {

        int cur = startFuel;
        int cnt = 0;
        int i = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        while (true) {
            if (cur >= target)
                return cnt;

            while (i < stations.length && stations[i][0]<=cur)
                pq.add(stations[i++]);

            if (pq.isEmpty())
                break;

            cur += pq.poll()[1];
            cnt++;
        }

        return -1;

    }


}
