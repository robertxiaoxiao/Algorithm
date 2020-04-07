package LeetCode.Array;

import java.util.LinkedList;
import java.util.List;

public class q54 {

    static boolean[][] visited;
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new LinkedList<>();

        int n = matrix.length;
        if (n == 0) return ans;
        int m = matrix[0].length;
        if (m == 0) return ans;
        visited = new boolean[n][m];

        int size = 0;
        int sum = n * m;
        int i = 0, j = 0, dir = 0;
        while (size < sum) {
            size++;
            visited[i][j] = true;
            ans.add(matrix[i][j]);
            int ni = i + dirs[dir][0];
            int nj = j + dirs[dir][1];
            if (ni >= n || ni < 0 || nj >= m || nj < 0 || visited[ni][nj]) {
                dir = (dir + 1) % 4;
                ni = i + dirs[dir][0];
                nj = j + dirs[dir][1];
            }

            i = ni;
            j = nj;
        }

        return ans;
    }

}
