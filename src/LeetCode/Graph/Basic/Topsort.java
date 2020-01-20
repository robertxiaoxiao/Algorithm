package LeetCode.Graph.Basic;/*
 * @author: Robert
 * @date:  2020/1/15/015
 * @description:
 */

import java.beans.Visibility;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Topsort {

    public int[] Topsort(List<List<Integer>> graph) {
        int n = graph.size();
        int[] states = new int[n];
        List<Integer> vorder = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (dfs(i, graph, states, vorder))
                return new int[0];
        int[] ans = new int[n];
        int k = 0;
        for (int i : vorder)
            ans[k++] = i;
        return ans;
    }

    public boolean dfs(int cur, List<List<Integer>> graph, int[] states, List<Integer> vorder) {
        if (states[cur] == 1)
            //cycle exists;
            return true;
        if (states[cur] == 2)
            //cycle NOT  exists;
            return false;
        states[cur] = 1;
        for (int neighbor : graph.get(cur))
            if (dfs(neighbor, graph, states, vorder))
                return true;
        vorder.add(0, cur);
        states[cur] = 2;
        return false;
    }


}
