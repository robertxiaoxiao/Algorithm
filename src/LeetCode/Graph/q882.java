package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/19/019
 * @description:
 */

import RelatedImpl.SegmentTree;

import java.text.Normalizer;
import java.util.*;

public class q882 {

    public int reachableNodes(int[][] edges, int M, int N) {
        int[] dist = new int[N];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        List<List<int[]>> ajlist = new LinkedList<>();
        for (int i = 0; i < N; i++)
            ajlist.add(new LinkedList<>());
        for (int[] edge : edges) {
            ajlist.get(edge[0]).add(new int[]{edge[1], edge[2]});
            // NOTICE  THERE UNDIRECTED GRAPH IT SHOULD BUILD TWO EDGES
            ajlist.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        boolean[] visited = new boolean[N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int[] start = new int[]{0, 0};
        pq.add(start);
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curN = cur[0];
            if (visited[curN] || dist[curN] > M)
                continue;
            visited[curN] = true;
            for (int[] next : ajlist.get(curN)) {
                int nextN = next[0];
                if (visited[nextN])
                    continue;
                int ncost = dist[curN] + next[1] + 1;
                if (dist[nextN] == -1 || ncost < dist[nextN]) {
                    dist[nextN] = ncost;
                    pq.add(new int[]{nextN, ncost});
                }
            }
        }
        int ans = 0;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int n = edge[2];
            int left = M - dist[from];
            int right = M - dist[to];
            if (dist[from] == -1 || dist[from] > M)
                left = 0;
            if (dist[to] == -1 || dist[to] > M)
                right = 0;
            ans += Math.min(left + right, n);
        }
        for (int i = 0; i < N; i++)
            if (dist[i] == -1 || dist[i] > M)
                continue;
            else
                ans++;
        return ans;
    }

    public int reachableNodesEfficient(int[][] edges, int M, int N) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i = 0; i < N; i++) map.put(i, new HashMap<>());
        boolean[] visited = new boolean[N];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) ->(b[1] - a[1]));
        for(int[] e : edges) {
            map.get(e[0]).put(e[1], e[2]);
            map.get(e[1]).put(e[0], e[2]);
        }

        int nodes = 0;
        queue.offer(new int[]{0, M});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int src = cur[0], move = cur[1];
            if(visited[src]) continue;
            visited[src] = true;
            nodes++;
            for(int next : map.get(src).keySet()) {
                int middleNodes = map.get(src).get(next);
                int leftSteps = move - middleNodes - 1;
                if(!visited[next]) {
                    if(leftSteps > 0) {
                        nodes += middleNodes;
                        map.get(next).put(src, 0);
                        queue.offer(new int[]{next, leftSteps});
                    }
                    else {
                        nodes += move;
                        map.get(next).put(src, map.get(next).get(src) - move);
                    }
                }
                else {
                    nodes += Math.min(move, middleNodes);
                }
            }
        }
        return nodes;
    }

}
