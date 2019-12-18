package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/18/018
 * @description:
 */

import java.util.*;

public class q675 {

    static class node {
        int pos;
        int height;

        public node(int pos, int height) {
            this.pos = pos;
            this.height = height;
        }
    }

    public int cutOffTree(List<List<Integer>> forest) {
        int n = forest.size();
        int m = forest.get(0).size();
        PriorityQueue<node> pq = new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return o1.height - o2.height;
            }
        });
        int[][] forests = new int[n][m];
        List<Integer> temp;
        for (int i = 0; i < n; i++) {
            temp = forest.get(i);
            for (int j = 0; j < m; j++) {
                int height = temp.get(j);
                forests[i][j] = height;
                if (height != 0) {
                    node tnode = new node(i * 50 + j, height);
                    pq.add(tnode);
                }
            }
        }
        int start = 0;
        int steps = 0;
        while (!pq.isEmpty()) {
            node curnode = pq.poll();
            int tpos = curnode.pos;
            int nsteps = bfs(forests, start, tpos);
            if (nsteps == -1)
                return -1;
            else {
                steps += nsteps;
                System.out.printf("%d %d %d\r\n", start, tpos, steps);
                start = tpos;
            }
        }
        return steps;
    }

    static int[] dirs = {0, -1, 0, 1, 0};

    public int bfs(int[][] a, int start, int k) {
        int tx = k / 50;
        int ty = k % 50;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        HashSet<Integer> seen = new HashSet<>();
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int cur = queue.poll();
                int curx = cur / 50;
                int cury = cur % 50;
                if (curx == tx && cury == ty)
                    return steps;
                for (int i = 0; i < 4; i++) {
                    int nx = curx + dirs[i];
                    int ny = cury + dirs[i + 1];
                    int next = nx * 50 + ny;
                    if (nx < 0 || ny < 0 || nx >= a.length || ny >= a[0].length || a[nx][ny] == 0 || seen.contains(next))
                        continue;
                    queue.add(next);
                    seen.add(next);
                }
            }
            ++steps;
        }
        return -1;
    }
}
