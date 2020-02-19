package leetcodeTest.Week15;/*
 * @author: Robert
 * @date:  2020/2/16/016
 * @description:
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class q3 {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int n = events.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0;
        int cur = 0;
        for (int i = 1; i <= 100000; i++) {
            while (cur < n && events[cur][0] == i) {
                pq.add(events[cur][1]);
                cur++;
            }
            while (!pq.isEmpty()) {
                int r = pq.poll();
                if (r < i)
                    continue;
                cnt++;
                break;
            }
        }
        return cnt;
    }
}
