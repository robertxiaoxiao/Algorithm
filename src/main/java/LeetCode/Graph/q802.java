package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/8/008
 * @description:
 */

import java.util.*;

public class q802 {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new LinkedList<>();
        int n = graph.length;
        int[] state = new int[n];
        for (int i = 0; i < n; i++)
            dfs(i, graph, state);

        for (int i = 0; i < n; i++)
            if (state[i] == 2)
                res.add(i);
        Collections.sort(res);
        return res;
    }

    public boolean dfs(int cur, int[][] graph, int[] state) {
        if (graph[cur].length == 0) {
            state[cur] = 2;
            return true;
        }

        if (state[cur] == 2)
            return true;
        if (state[cur] == -1)
            return false;

        state[cur] = 1;
        boolean flag = true;
        for (int next : graph[cur]) {
            if (state[next] == 1) {
                state[cur] = -1;
                return false;
            }
            flag &= dfs(next, graph, state);
        }

        if (flag) {
            state[cur] = 2;
            return true;
        }
        state[cur] = -1;
        return false;
    }

    public List<Integer> eventualSafeNodesUsingnaive(int[][] graph) {
        List<Integer> res = new LinkedList<>();
        int n = graph.length;

        HashSet<Integer> terminal = new HashSet<>();
        HashSet<Integer> unsafe = new HashSet<>();
        boolean[] visiting = new boolean[n];
        for (int i = 0; i < n; i++)
            dfs(i, graph, terminal, unsafe, visiting);
        for (int i : terminal)
            res.add(i);
        Collections.sort(res);
        return res;
    }

    public boolean dfs(int cur, int[][] graph, HashSet<Integer> terminal, HashSet<Integer> unsafe, boolean[] visiting) {
        if (graph[cur].length == 0) {
            terminal.add(cur);
            return true;
        }

        if (terminal.contains(cur))
            return true;
        if (unsafe.contains(cur))
            return false;

        boolean flag = true;
        visiting[cur] = true;
        for (int next : graph[cur]) {
            if (visiting[next]) {
                unsafe.add(cur);
                return false;
            }
            flag &= dfs(next, graph, terminal, unsafe, visiting);
        }
        visiting[cur] = false;
        if (flag)
            terminal.add(cur);
        else
            unsafe.add(cur);
        return flag;
    }

}
