package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/13/013
 * @description:
 */

import java.util.*;

public class q787 {


    public int findCheapestPriceUsingdfs(int n, int[][] flights, int src, int dst, int K) {

        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            int p1 = flight[0];
            int p2 = flight[1];
            int cost = flight[2];
            if (!graph.containsKey(p1))
                graph.put(p1, new LinkedList<>());
            graph.get(p1).add(new int[]{p2, cost});
        }

        boolean[] visited = new boolean[n];
        min = Integer.MAX_VALUE;
        dfs(src, K + 1, 0, dst, visited, graph);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    static int min = Integer.MAX_VALUE;

    public int bfs(int src, int k, int dst, HashMap<Integer, List<int[]>> graph) {
        Queue<int[]> queue = new LinkedList<>();
        int steps = 0;
        queue.add(new int[]{src, 0});
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (steps > k + 1)
                break;
            while (size > 0) {
                size--;
                int[] curArr = queue.poll();
                int cur = curArr[0];
                int cost = curArr[1];
                if (cur == dst)
                    ans = Math.min(ans, cost);
                if (graph.get(cur) == null)
                    continue;
                for (int[] next : graph.get(cur)) {
                    if (cost + next[1] > ans)
                        continue;
                    queue.add(new int[]{next[0], cost + next[1]});
                }
            }
            steps++;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public void dfs(int cur, int k, int cost, int dst, boolean[] visited, HashMap<Integer, List<int[]>> graph) {

        if (cost > min)
            return;

        if (cur == dst) {
            min = cost;
            return;
        }

        if (k == 0)
            return;

        if (graph.get(cur) == null)
            return;

        for (int[] next : graph.get(cur)) {
            int np = next[0];
            int ncost = next[1];
            if (visited[np])
                continue;
            visited[np] = true;
            dfs(np, k - 1, cost + ncost, dst, visited, graph);
            visited[np] = false;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 2][n];
        for (int k = 0; k < K + 2; k++)
            Arrays.fill(dp[k], Integer.MAX_VALUE / 2);
        dp[0][src] = 0;
        for (int k = 1; k <= K + 1; k++) {
            dp[k][src] = 0;
            for (int[] flight : flights) {
                int p1 = flight[0];
                int p2 = flight[1];
                int cost = flights[p1][p2];
                dp[k][p2] = Math.min(dp[k][p2], dp[k - 1][p1] + cost);
            }
        }
        return dp[K + 1][dst] > Integer.MAX_VALUE / 2 ? -1 : dp[K + 1][dst];
    }


}
