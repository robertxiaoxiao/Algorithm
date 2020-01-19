package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/19/019
 * @description:
 */

import java.util.*;

public class q743 {

    static class node {
        int node;
        int cost;

        public node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        boolean[] visited = new boolean[N + 1];
        List<HashMap<Integer, Integer>> graph = new LinkedList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new HashMap<>());
        for (int[] edge : times) {
            int node = edge[0];
            int tonode = edge[1];
            int cost = edge[2];
            graph.add(node, new HashMap<>());
            graph.get(node).put(tonode, cost);
        }
        int[] dist = new int[N + 1];
        Arrays.fill(dist, -1);
        node start = new node(K, 0);
        dist[K] = 0;
        PriorityQueue<node> pq = new PriorityQueue<node>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return o1.cost - o2.cost;
            }
        });
        pq.add(start);
        while (!pq.isEmpty()) {
            node curn = pq.poll();
            int curnode = curn.node;
            visited[curnode] = true;
            for (int nextnode : graph.get(curnode).keySet()) {
                if (visited[nextnode])
                    continue;
                int cost = graph.get(curnode).get(nextnode);
                if (dist[nextnode] == -1 || dist[curnode] + cost < dist[nextnode]) {
                    int nextcost = dist[curnode] + cost;
                    node next = new node(nextnode, nextcost);
                    dist[nextnode] = nextcost;
                    pq.add(next);
                }
            }
        }
        int max = -1;
        for (int i = 1; i <= N; i++) {
            System.out.print(dist[i]+" ");
            if (dist[i] == -1)
                return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }

    public int networkDelayTimeMoreSpacious(int[][] times, int N, int K) {
        List<int[]>[] adjacency = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adjacency[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            adjacency[time[0]].add(time);
        }
        Set<Integer> visited = new HashSet<>(N);
        visited.add(K);
        PriorityQueue<int[]> djikstra = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        for (int[] edge : adjacency[K]) {
            djikstra.add(edge);
        }
        while (!djikstra.isEmpty()) {
            int[] edge = djikstra.poll();
            if (visited.contains(edge[1])) {
                continue;
            }
            visited.add(edge[1]);
            for (int[] newEdge : adjacency[edge[1]]) {
                if (visited.contains(newEdge[1])) {
                    continue;
                }
                newEdge[2] += edge[2];
                djikstra.add(newEdge);
            }
            if (visited.size() == N) {
                return edge[2];
            }
        }
        return -1;
    }
}
