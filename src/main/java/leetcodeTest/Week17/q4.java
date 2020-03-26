package leetcodeTest.Week17;

import java.util.*;

public class q4 {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int t = 100;

    //0-1 bfs + deque
    public int minCostDequeUsingBFS(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Deque<int[]> deque = new LinkedList<>();
        HashSet<Integer> seen = new HashSet<>();
        deque.add(new int[]{0, 0, 0});

        while (!deque.isEmpty()) {
            int[] temp = deque.poll();
            int cost = temp[0];
            int cx = temp[1];
            int cy = temp[2];
            int txy = cx * t + cy;
            if (cx == n - 1 && cy == m - 1)
                return cost;
            if (seen.contains(txy))
                continue;
            seen.add(txy);
            for (int i = 0; i < 4; i++) {
                int nx = cx + dirs[i][0];
                int ny = cy + dirs[i][1];
                int tx = nx * t + ny;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || seen.contains(tx))
                    continue;
                if (i == grid[cx][cy] - 1) {
                    deque.addFirst(new int[]{cost, nx, ny});
                } else {
                    deque.addLast(new int[]{cost + 1, nx, ny});
                }
            }
        }
        return -1;
    }

    // dijkstra relax +bfs
    public int minCostDequeUsingRelax(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[]{0, 0, 0});
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] temp = deque.poll();
            int cost = temp[0];
            int cx = temp[1];
            int cy = temp[2];
            for (int i = 0; i < 4; i++) {
                int tcost = cost;
                int nx = cx + dirs[i][0];
                int ny = cy + dirs[i][1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (i == grid[cx][cy] - 1) {
                    if (tcost < dist[nx][ny]) {
                        dist[nx][ny] = tcost;
                        deque.addFirst(new int[]{tcost, nx, ny});
                    }
                } else {
                    tcost++;
                    if (tcost < dist[nx][ny]) {
                        dist[nx][ny] = tcost;
                        deque.addLast(new int[]{tcost, nx, ny});
                    }
                }
            }
        }
        return dist[n - 1][m - 1];
    }

   public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] t1, int[] t2) {
                return t1[0] - t2[0];
            }
        });
        HashSet<Integer> seen = new HashSet<>();
        pq.add(new int[]{0, 0, 0});
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        dist[0][0] = 0;
        while (!pq.isEmpty()) {
            int[] temp = pq.poll();
            int cost = temp[0];
            int cx = temp[1];
            int cy = temp[2];
            int txy = cx * t + cy;
            if (seen.contains(txy))
                continue;
            seen.add(txy);
            for (int i = 0; i < 4; i++) {
                int nx = cx + dirs[i][0];
                int ny = cy + dirs[i][1];
                int tx = nx * t + ny;
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || seen.contains(tx))
                    continue;
                if (i != grid[cx][cy] - 1) {
                    cost++;
                }
                if (cost > dist[nx][ny])
                    continue;
                dist[nx][ny] = cost;
                pq.add(new int[]{cost, nx, ny});
            }
        }
        return dist[n - 1][m - 1];
    }


}
