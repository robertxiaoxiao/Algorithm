package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.List;

public class q120 {

    public int minimumTotal(List<List<Integer>> triangle) {

        int height = triangle.size();
        int len = triangle.get(height - 1).size();
        int start = 0;
        int[][] dp = new int[height][len];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < height; i++)
            for (int j = 0; j < i + 1; j++) {
                if (j == 0)
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                else if (j == i)
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
            }
        int min = Integer.MAX_VALUE;
        for (int num : dp[height - 1])
            if (num < min)
                min = num;

        return min;
    }
}
