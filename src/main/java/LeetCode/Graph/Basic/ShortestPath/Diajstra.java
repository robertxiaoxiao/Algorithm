package LeetCode.Graph.Basic.ShortestPath;/*
 * @author: Robert
 * @date:  2020/1/15/015
 * @description:
 */

import java.util.*;

public class Diajstra {

    static class Node {
        int dis;
        int node;

        public Node(int node, int dis) {
            this.dis = dis;
            this.node = node;
        }
    }

    public static int diajstra(int[][] graph, int src, int dst) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dis - o2.dis;
            }
        });
        int n = graph.length;
        int[] dist = new int[n];
        // trace the path
        int[] pre = new int[n];
        pre[src] = -1;
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[src] = 0;
        Node str = new Node(src, 0);
        pq.add(str);
        boolean[] seen = new boolean[graph.length];
        while (!pq.isEmpty()) {
            Node curnode = pq.poll();
            int cn = curnode.node;
            int curdis = curnode.dis;
            //OPTIMIZATION
            if (dist[cn] < curdis)
                continue;
            seen[cn] = true;
            if (cn == dst)
                return curdis;
            for (int i = 0; i < n; i++) {
                if (graph[cn][i] == -1 || seen[i])
                    continue;
                int nextdis = curdis + graph[cn][i];
                if (nextdis < dist[i]) {
                    dist[i] = nextdis;
                    pre[i] = cn;
                    Node next = new Node(i, nextdis);
                    pq.add(next);
                }
            }
        }
        //print the path
        List<Integer> path = new LinkedList<>();
        int end = dst;
        while (pre[end] != -1) {
            path.add(end);
            end = pre[end];
        }
        path.add(src);
        Collections.reverse(path);
        System.out.printf("cur node to %d : ", dst);
        for (int i : path)
            System.out.printf("->%d", i);
        System.out.println();
        return dist[dst];
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, -1, -1, 5},
                {-1, 0, 1, -1, 2},
                {-1, -1, 0, 4, -1},
                {7, -1, 6, 0, -1},
                {-1, 3, 9, 2, 0}
        };
        for (int i = 1; i < 5; i++)
            System.out.println(diajstra(graph, 0, i));
    }
}
