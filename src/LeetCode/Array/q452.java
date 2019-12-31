package LeetCode.Array;/*
 * @author: Robert
 * @date:  2019/12/31/031
 * @description:
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class q452 {
    // scam line

    public int findMinArrowShots(int[][] points) {
        // sort by end
        if (points.length == 0)
            return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int sum = 1;
        for (int[] point : points)
            pq.add(point);
        int[] arr = pq.poll();
        int end = arr[1];
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            if (temp[0] < end)
                continue;
            end = temp[1];
            sum++;
        }
        return sum;
    }

    public int findMinArrowShotsUsingArr(int[][] points) {
        int n = points.length;
        if (n == 0)
            return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int j = 1;
        int sum = 1;
        int curend = points[0][1];
        while (j < n) {
            if (points[j][0] <= curend) {
                j++;
                continue;
            }

            curend = points[j][1];
            sum++;
            j++;
        }
        return sum;
    }
}
