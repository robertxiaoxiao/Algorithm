package LeetCode.DP;

import java.util.Arrays;

public class q1105 {

    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        for (int i = 0; i < n; i++) {
            int w = 0;
            int h = 0;
            for (int j = i; j >= 0; j--) {
                if (w + books[j][0] > shelf_width)
                    break;
                w += books[j][0];
                h = Math.max(h, books[j][1]);
                dp[i] = Math.min(dp[i], (j == 0 ? 0 : dp[j - 1]) + h);
            }
        }

        return dp[n - 1];
    }


}
