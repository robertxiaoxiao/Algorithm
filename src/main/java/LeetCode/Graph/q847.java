package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/19/019
 * @description:
 */

import java.util.LinkedList;
import java.util.Queue;

public class q847 {

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        // curnode visitedNode
        int[][] state = new int[n][1 << n];

        //NOTICE : edge case
        if (n == 0 || n == 1)
            return 0;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            state[i][1 << i] = 1;
            queue.add(new int[]{i, 1 << i});
        }
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int[] cur = queue.poll();
                int curnode = cur[0];
                int visitednodes = cur[1];
                for (int next : graph[curnode]) {
                    int nextvisited = visitednodes | (1 << next);
                    if (nextvisited == ((1 << n) - 1))
                        return steps + 1;
                    if (state[next][nextvisited] == 1)
                        continue;
                    state[next][nextvisited] = 1;
                    queue.add(new int[]{next, nextvisited});
                }
            }
            steps++;
        }
        return -1;
    }
}
