package LeetCode.Graph.Basic.MST;/*
 * @author: Robert
 * @date:  2020/1/20/020
 * @description:
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Prime {

    class edge {
        int to;
        int cost;

        public edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "edge{" +
                    "to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }

    PriorityQueue<edge> pq;
    boolean[] visited;
    List<List<edge>> adjlist;
    edge[] path;

    public Prime(int n) {
        adjlist = new LinkedList<>();
        for (int i = 0; i < n; i++)
            adjlist.add(new LinkedList<>());
        visited = new boolean[n];
        pq = new PriorityQueue<>(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                return o1.cost - o2.cost;
            }
        });
        path = new edge[n - 1];
    }

    public void addedge(int from, int to, int cost) {
        adjlist.get(from).add(new edge(to, cost));
        adjlist.get(to).add(new edge(from, cost));
    }

    public int lazyPrime(int n, int s) {
        int ans = 0;
        int edgenums = 0;
        visited[s] = true;
        for (edge e : adjlist.get(s))
            pq.add(e);

        while (!pq.isEmpty() && edgenums != n - 1) {
            edge e = pq.poll();
            int tnode = e.to;
            if (visited[tnode])
                continue;
            ans += e.cost;
            visited[tnode] = true;
            path[edgenums++] = e;
            for (edge en : adjlist.get(tnode)) {
                if (visited[en.to])
                    continue;
                pq.add(en);
            }
        }
        // not exist MST
        if (edgenums != n - 1)
            return -1;
        return ans;
    }

    public void printPath(int s) {
        for (edge e : path) {
            System.out.printf("to %d : %d\r\n", e.to, e.cost);
        }
    }

    public static void main(String[] args) {
        int n = 9;
        Prime prime = new Prime(n);
        prime.addedge(0, 1, 4);
        prime.addedge(0, 8, 8);
        prime.addedge(1, 8, 11);
        prime.addedge(1, 2, 8);
        prime.addedge(2, 3, 7);

        prime.addedge(3, 4, 9);
        prime.addedge(4, 5, 10);
        prime.addedge(3, 5, 14);
        prime.addedge(2, 5, 4);
        prime.addedge(6, 5, 2);

        prime.addedge(6, 7, 6);
        prime.addedge(6, 8, 1);
        prime.addedge(8, 7, 7);
        prime.addedge(2, 7, 2);

        System.out.println(prime.lazyPrime(n, 0));
        prime.printPath(0);

    }
}
