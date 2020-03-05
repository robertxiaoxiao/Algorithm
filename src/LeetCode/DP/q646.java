package LeetCode.DP;

import java.util.Arrays;
import java.util.Comparator;

public class q646 {


    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        int[] dpb = new int[n];
        int[] dpf = new int[n];
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        dpb[0] = 1;
        dpf[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            int[] cur = pairs[i];
            dpb[i] = 1;
            dpf[i] = 1;
            for (int j = 0; j < i; j++) {
                int[] pre = pairs[j];
                if (pre[1] < cur[0])
                    dpb[i] = Math.max(dpb[i], dpb[j] + 1);
                if (pre[0] > cur[1])
                    dpf[i] = Math.max(dpf[i], dpf[j] + 1);
            }
            max = Math.max(max, Math.max(dpb[i], dpf[i]));
        }
        return max;
    }

}
