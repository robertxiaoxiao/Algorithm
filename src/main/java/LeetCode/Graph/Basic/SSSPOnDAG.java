package LeetCode.Graph.Basic;/*
 * @author: Robert
 * @date:  2020/1/18/018
 * @description:
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SSSPOnDAG {

    // O(V+E)
    public int[] getShortestPathingraph(List<HashMap<Integer, Integer>> graph) {
        int n = graph.size();
        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE / 2);
        Topsort tsort = new Topsort();
        List<List<Integer>> tgraph = new LinkedList<>();
        for (int i = 0; i < n; i++)
            tgraph.add(new LinkedList<>(graph.get(i).keySet()));
        int[] order = tsort.Topsort(tgraph);
        ans[order[0]] = 0;
        for (int i : order)
            for (int next : graph.get(i).keySet())
                ans[next] = Math.min(ans[next], ans[i] + graph.get(i).get(next));
        return ans;
    }

    public int[] getLongestPathInGraph(List<HashMap<Integer, Integer>> graph) {
        int n = graph.size();
        for (int i = 0; i < n; i++)
            if (graph.get(i) != null)
                for (int key : graph.get(i).keySet())
                    graph.get(i).put(key, -graph.get(i).get(key));
        return getShortestPathingraph(graph);
    }

    public static void main(String[] args) {

        double c=Double.POSITIVE_INFINITY;
        double d=c+1;
        System.out.println(c);
        System.out.println(d);

    }

}
