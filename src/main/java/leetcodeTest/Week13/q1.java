package leetcodeTest.Week13;/*
 * @author: Robert
 * @date:  2020/2/2/002
 * @description:
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class q1 {

    public int[] kWeakestRows(int[][] mat, int k) {

        int m = mat.length;
        if (m == 0)
            return new int[0];
        int n = mat[0].length;
        if (n == 0)
            return new int[0];

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1])
                    return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < m; i++) {
            int cnt = 0;
            int j = 0;
            while (j < n && mat[i][j] == 1) {
                cnt++;
                j++;
            }
            pq.add(new int[]{i, j});
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++)
            res[i] = pq.poll()[0];
        return res;
    }


}
