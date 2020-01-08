package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/8/008
 * @description:
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class q210 {
    static List<List<Integer>> graph;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //NOTICE: LinkedList more expensive
        graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }
        int n = numCourses;
        int[] state = new int[n];
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (dfs(i, state, res))
                return new int[0];
        int[] order = new int[n];
        for (int i = 0; i < n; i++)
            order[i] = res.get(i);
        return order;
    }

    private boolean dfs(int i, int[] state, List<Integer> res) {
        if (state[i] == 1)
            return true;
        if (state[i] == 2)
            return false;
        state[i] = 1;
        for (int neigh : graph.get(i))
            if (dfs(neigh, state, res))
                return true;
        state[i] = 2;
        res.add(0, i);
        return false;
    }


}
