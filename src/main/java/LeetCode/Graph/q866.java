package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/13/013
 * @description:
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class q866 {

    public boolean possibleBipartition(int N, int[][] dislikes) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashSet<Integer> hset = new HashSet<>();
        for (int[] dislike : dislikes) {
            int c1 = dislike[0];
            int c2 = dislike[1];
            if (!graph.containsKey(c1))
                graph.put(c1, new LinkedList<>());
            if (!graph.containsKey(c2))
                graph.put(c2, new LinkedList<>());
            graph.get(c1).add(c2);
            graph.get(c2).add(c1);
        }
        return isBipartite(graph, N);
    }

    public boolean isBipartite(HashMap<Integer, List<Integer>> graph, int n) {
        int[] state = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (state[i] != 0)
                continue;
            if (!dfs(i, 1, state, graph))
                return false;
        }
        return true;
    }

    public boolean dfs(int cur, int precolor, int[] state, HashMap<Integer, List<Integer>> graph) {
        if (cur == state.length)
            return true;

        if (state[cur] == precolor)
            return false;

        int curcolor = 1 == precolor ? 2 : 1;
        if (state[cur] == curcolor)
            return true;

        if (state[cur] == 0) {
            state[cur] = curcolor;
            if (!graph.containsKey(cur))
                return true;
            for (int i : graph.get(cur)) {
                if (!dfs(i, curcolor, state, graph))
                    return false;
            }
        }
        return true;
    }


}
