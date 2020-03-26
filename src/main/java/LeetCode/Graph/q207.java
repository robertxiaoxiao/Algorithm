package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/8/008
 * @description:
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q207 {

    static List<List<Integer>> graph;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //NOTICE: LinkedList more expensive
        graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }
        int n=numCourses;
        int[] state = new int[n];
        for (int i = 0; i < n; i++)
            if (dfs(i, state))
                return false;
        return true;
    }
    private boolean dfs(int i, int[] state) {
        if (state[i] == 1)
            return true;
        if (state[i] == 2)
            return false;
        state[i] = 1;
        for (int neigh : graph.get(i))
            if (dfs(neigh, state))
                return true;
        state[i] = 2;
        return false;
    }

    public boolean topsort(int n) {
        // 0  unknown   1 visiting 2 visited
        int[] state = new int[n];
        for (int i = 0; i < n; i++)
            if (dfs(i, state))
                return false;
        return true;
    }



    public boolean topsortTOL(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        int cnt = 0;
        while (cnt < n) {
            int t = selectedNode(graph, visited);
            if (t == -1)
                return false;
            cnt++;
            visited[t] = true;
            for (int i = 0; i < n; i++)
                graph[t][i] = 0;
        }
        return true;
    }

    public int selectedNode(int[][] graph, boolean[] visited) {
        int n = graph.length;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            if (visited[i])
                continue;
            for (int j = 0; j < n; j++) {
                if (visited[j])
                    continue;
                if (graph[j][i] == 1) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                return i;
        }
        return -1;
    }

}
