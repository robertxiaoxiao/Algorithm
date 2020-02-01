package leetcodeTest.Week12;/*
 * @author: Robert
 * @date:  2020/1/26/026
 * @description:
 */

import java.util.*;

public class q3 {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] state = new int[n];
        int[] res = new int[n];
        int ans = 0;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE / 2);
            graph[i][i] = 0;
        }

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            int cost = edge[2];
            graph[i][j] = cost;
            graph[j][i] = cost;
        }

        int[][] dp = FW(graph);
        for (int i = 0; i < n; i++) {
            ans = 0;
            for (int j = 0; j < n; j++) {
                if (dp[i][j] <= distanceThreshold)
                    ans++;
            }
            res[i] = ans;
        }

        int min = n;
        int tar = -1;
        for (int i = 0; i < n; i++) {
            if (res[i] < min) {
                min = res[i];
                tar = i;
            }
            if (res[i] == min && tar < i)
                tar = i;
        }
        return tar;
    }

    public int[][] FW(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                dp[i][j] = matrix[i][j];
        }

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j])
                        dp[i][j] = dp[i][k] + dp[k][j];

                }

        return dp;
    }
}
