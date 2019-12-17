package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/17/017
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q934 {

    static int[] dirs = {0, -1, 0, 1, 0};

    public int shortestBridge(int[][] A) {
        // to find the first boarder and the bfs to find the steps ;
        int n = A.length;
        int m = A[0].length;
        int start = -1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    start = i * 100 + j;
                    break;
                }
            }
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> boarder = new LinkedList<>();
        // to get the boarder
        queue.add(start);
        HashSet<Integer> seen = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int cur = queue.poll();
                int curx = cur / 100;
                int cury = cur % 100;
                boolean flag = false;
                for (int i = 0; i < 4; i++) {
                    int nextx = curx + dirs[i];
                    int nexty = cury + dirs[i + 1];
                    int next = nextx * 100 + nexty;
                    if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= m)
                        continue;
                    if (seen.contains(next))
                        continue;

                    if (A[nextx][nexty] == 1) {
                        queue.add(next);
                        seen.add(next);
                    } else
                        flag = true;
                }
                if (flag)
                    boarder.add(cur);
            }
        }
        // to expand the boarder
        int steps = 0;
        while (!boarder.isEmpty()) {
            int size = boarder.size();
            while (size > 0) {
                --size;
                int curboarder = boarder.poll();
                int cbx = curboarder / 100;
                int cby = curboarder % 100;
                for (int i = 0; i < 4; i++) {
                    int ncbx = cbx + dirs[i];
                    int ncby = cby + dirs[i + 1];
                    int next = ncbx * 100 + ncby;
                    if (ncbx < 0 || ncby < 0 || ncbx >= n || ncby >= m)
                        continue;

                    if (seen.contains(next))
                        continue;

                    if (A[ncbx][ncby] == 1) {
                        return steps;

                    } else {
                        seen.add(next);// mark 1 but not real mark ;
                        boarder.add(next);
                    }
                }
            }
            steps++;
        }
        return steps;
    }

    // proceed the boarder (A[i][j]==0)
    public int shortestBridgeUsingDFS(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int start = -1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    start = i * 100 + j;
                    break;
                }
            }
        Queue<Integer> boarder = new LinkedList<>();
        // to get the boarder
        dfs(A, start, boarder);
        // to expand the boarder
        int steps = 0;
        while (!boarder.isEmpty()) {
            int size = boarder.size();
            while (size > 0) {
                --size;
                int curboarder = boarder.poll();
                int cbx = curboarder / 100;
                int cby = curboarder % 100;
                if (A[cbx][cby] == -1)
                    continue;
                A[cbx][cby] = -1;
                for (int i = 0; i < 4; i++) {
                    int ncbx = cbx + dirs[i];
                    int ncby = cby + dirs[i + 1];
                    int next = ncbx * 100 + ncby;
                    if (ncbx < 0 || ncby < 0 || ncbx >= n || ncby >= m)
                        continue;

                    if (A[ncbx][ncby] == -1)
                        continue;

                    if (A[ncbx][ncby] == 1) {
                        return steps + 1;
                    } else {
                        ;// mark 1 but not real mark ;
                        boarder.add(next);
                    }
                }
            }
            steps++;
        }
        return steps;
    }

    public static void dfs(int[][] A, int cur, Queue<Integer> boarder) {
        int curx = cur / 100;
        int cury = cur % 100;
        A[curx][cury] = -1;
        for (int i = 0; i < 4; i++) {
            int nx = curx + dirs[i];
            int ny = cury + dirs[i + 1];
            int next = nx * 100 + ny;
            if (nx < 0 || ny < 0 || nx >= A.length || ny >= A[0].length || A[nx][ny] == -1)
                continue;
            if (A[nx][ny] == 0) {
                boarder.add(nx * 100 + ny);
            } else {
                dfs(A, next, boarder);
            }
        }
    }

    public static void print(int[][] A) {
        int n = A.length;
        int m = A[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(A[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        int[][] a = {
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 1, 1}
        };
        HashSet<Integer> seen = new HashSet<>();
        Queue<Integer> boarder = new LinkedList<>();
        int start = 101;
        print(a);
        dfs(a, 101, boarder);
        print(a);
        System.out.println(boarder.size());
        for (int num : boarder)
            System.out.print(num + "  ");
    }
}
