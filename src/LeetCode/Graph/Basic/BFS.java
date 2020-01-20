package LeetCode.Graph.Basic;/*
 * @author: Robert
 * @date:  2020/1/20/020
 * @description:
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    List<List<Integer>> ajlist = new LinkedList<>();

    public BFS(int n) {
        for (int i = 0; i <= n; i++)
            ajlist.add(new LinkedList<>());
    }

    // O(V+E)
    public void bfs(int n, int str) {
        // 0 white 1 gray 2 black
        n++;
        int[] color = new int[n];
        color[str] = 1;

        int[] dist = new int[n];
        int[] path = new int[n];
        Arrays.fill(path, -1);
        dist[str] = 0;
        path[str] = str;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(str);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : ajlist.get(cur)) {
                if (color[next] == 0) {
                    color[next] = 1;
                    dist[next] = dist[cur] + 1;
                    path[next] = cur;
                    queue.add(next);
                }
            }
            color[cur] = 2;
        }
        for (int i = 1; i < n; i++)
            System.out.printf("node %d the layer is %d\r\n", i, dist[i]);
    }

    public void addEdge(int from, int to) {
        ajlist.get(from).add(to);
        ajlist.get(to).add(from);
    }


    public static void main(String[] args) {
        BFS bfssolver = new BFS(8);
        bfssolver.addEdge(1, 8);
        bfssolver.addEdge(1, 2);
        bfssolver.addEdge(2, 7);
        bfssolver.addEdge(3, 7);
        bfssolver.addEdge(3, 6);
        bfssolver.addEdge(7, 6);
        bfssolver.addEdge(3, 4);
        bfssolver.addEdge(6, 4);
        bfssolver.addEdge(6, 5);
        bfssolver.addEdge(4, 5);
        bfssolver.bfs(8, 2);
    }


}
