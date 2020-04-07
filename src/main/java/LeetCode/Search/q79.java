package LeetCode.Search;

public class q79 {

    static String w;
    static int len, n, m;
    static char[][] bd;
    static int[] dir = {1, 0, -1, 0, 1};
    static boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        n = board.length;
        if (n == 0) return false;
        m = board[0].length;
        if (m == 0) return false;
        w = word;
        len = word.length();
        bd = board;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited = new boolean[n][m];
                    if (dfs(0, i, j))
                        return true;
                }
            }
        return false;
    }

    public boolean dfs(int i, int si, int sj) {

        if (i == len)
            return true;

        if (bd[si][sj] != w.charAt(i))
            return false;

        if (si < 0 || si >= n || sj < 0 || sj >= m || visited[si][sj])
            return false;

        visited[si][sj] = true;
        for (int d = 0; d < 4; d++) {
            int ni = si + dir[d];
            int nj = sj + dir[d + 1];
            if (dfs(i + 1, ni, nj))
                return true;
        }
        // reset when dfs
        visited[si][sj] = false;
        return false;
    }

}
