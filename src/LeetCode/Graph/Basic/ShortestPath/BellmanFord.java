package LeetCode.Graph.Basic.ShortestPath;/*
 * @author: Robert
 * @date:  2020/1/15/015
 * @description:
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BellmanFord {

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public int[] getShortestPath(int src, List<Edge> edges, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[src] = 0;
        // Only in the worst case does it take V-1 iterations for the Bellman-Ford
        // algorithm to complete. Another stopping condition is when we're unable to
        // relax an edge, this means we have reached the optimal solution early.
        boolean relaxedEdge = true;
        for (int i = 0; i < n && relaxedEdge; i++)
            relaxedEdge = false;
        for (Edge edge : edges) {
            if (dist[edge.from] + edge.cost < dist[edge.to]) {
                dist[edge.to] = dist[edge.from] + edge.cost;
                relaxedEdge = true;
            }
        }
        //propogate the negative cycles
        relaxedEdge = true;
        for (int i = 0; i < n && relaxedEdge; i++)
            relaxedEdge = false;
        for (Edge edge : edges) {
            //directly in the negative cycle and reachable by negative cycle
            if (dist[edge.from] + edge.cost < dist[edge.to]) {
                dist[edge.to] = Integer.MIN_VALUE;
                relaxedEdge = true;
            }
        }
        return dist;
    }

}
