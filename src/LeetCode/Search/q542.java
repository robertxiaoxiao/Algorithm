package LeetCode.Search;/*
 * @author: Robert
 * @date:  2020/1/5/005
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q542 {
    public int[][] updateMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0)
                    res[i][j] = 0;
                else
                    res[i][j] = find(matrix, i, j);
            }
        return res;
    }

    static int[] dirs = {0, 1, 0, -1, 0};
    static int base = 10000;

    public int find(int[][] matrix, int x, int y) {

        int n = matrix.length;
        int m = matrix[0].length;

        Queue<Integer> queue = new LinkedList<>();
        int t = x * base + y;
        HashSet<Integer> seen = new HashSet<>();
        queue.add(t);
        seen.add(t);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int cur = queue.poll();
                int cx = cur / base;
                int cy = cur % base;
                for (int i = 0; i < 4; i++) {
                    int nx = cx + dirs[i];
                    int ny = cy + dirs[i + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                        continue;
                    if (matrix[nx][ny] == 0)
                        return steps + 1;
                    int ns = nx * base + ny;
                    if (seen.contains(ns))
                        continue;
                    queue.add(ns);
                    seen.add(ns);
                }
            }
            steps++;
        }
        return -1;
    }


}
