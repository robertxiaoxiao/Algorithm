package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/20/020
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class q864 {
    public int shortestPathAllKeys(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        char[][] chs = new char[n][m];
        int str = -1;
        int rows = 0;
        int knums = 0;
        for (String row : grid) {
            for (int i = 0; i < m; i++) {
                char ch = row.charAt(i);
                chs[rows][i] = ch;
                if (ch == '@') {
                    str = (rows << 16) | (i << 8);
                    chs[rows][i] = '.';
                }
                if (ch >= 'a' && ch <= 'f') {
                    knums++;
                }
            }
            rows++;
        }
        HashSet<Integer> visited = new HashSet<>();
        int[] dirs = {1, 0, -1, 0, 1};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(str);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                int cur = queue.poll();
                if (visited.contains(cur))
                    continue;
                visited.add(cur);
                int keys = cur % (1 << 8);
                int cx = cur / (1 << 16);
                int cy = (cur >> 8) % (1 << 8);
                for (int k = 0; k < 4; k++) {
                    int nx = cx + dirs[k];
                    int ny = cy + dirs[k + 1];
                    if (nx >= n || nx < 0 || ny < 0 || ny >= m || chs[nx][ny] == '#')
                        continue;
                    char cnext = chs[nx][ny];
                    if (cnext == '.') {
                        queue.add(getState(nx, ny, keys));
                        continue;
                    }
                    if (cnext >= 'A' && cnext <= 'F') {
                        int c = cnext - 'A';
                        int ns = getState(nx, ny, keys);
                        if ((keys & (1 << c)) == 0) {
                            continue;
                        } else {
                            queue.add(ns);
                        }
                    }
                    if (cnext >= 'a' && cnext <= 'f') {
                        int c = cnext - 'a';
                        int nkeys = keys | (1 << c);
                        if (nkeys == ((1 << knums) - 1))
                            return steps + 1;
                        queue.add(getState(nx, ny, nkeys));
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public int getState(int x, int y, int keys) {
        return ((x << 16) | (y << 8) | keys);
    }
}
