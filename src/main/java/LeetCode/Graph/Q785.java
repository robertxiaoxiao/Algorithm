package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/13/013
 * @description:
 */

import javax.swing.plaf.nimbus.State;
import java.util.LinkedList;
import java.util.Queue;

public class Q785 {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] state = new int[n];
        for (int i = 0; i < n; i++) {
            if (state[i] != 0)
                continue;
            if (!bfs(i, 1, state, graph))
                return false;
        }
        return true;
    }

    // 1 2
    public boolean dfs(int cur, int precolor, int[] state, int[][] graph) {
        if (cur == state.length)
            return true;
        if (state[cur] == precolor)
            return false;
        int curcolor = 1 == precolor ? 2 : 1;
        if (state[cur] == curcolor)
            return true;
        if (state[cur] == 0) {
            state[cur] = curcolor;

            for (int i : graph[cur]) {
                if (!dfs(i, curcolor, state, graph))
                    return false;
            }
        }
        return true;
    }

    public boolean bfs(int str, int precolor, int[] state, int[][] graph) {
        int curcolor = 1 == precolor ? -1 : 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(str);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int cur = queue.poll();
                if (state[cur] == -curcolor)
                    return false;
                if (state[cur] == curcolor)
                    continue;
                state[cur] = curcolor;
                for (int next : graph[cur])
                    queue.add(next);
            }
            curcolor = -curcolor;
        }
        return true;
    }

}
