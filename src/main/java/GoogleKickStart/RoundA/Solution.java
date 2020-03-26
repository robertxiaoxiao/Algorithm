package GoogleKickStart.RoundA;


import java.util.Scanner;


public class Solution {

    static class TreeNode {
        TreeNode[] childs;
        int cnt;

        public TreeNode() {
            childs = new TreeNode[26];
            cnt = 0;
        }
    }

    static int n;
    static int K;

    public static void dfs(TreeNode cur, String s, int p) {
        if (p == s.length()) {
            cur.cnt += 1;
            return;
        }
        int x = s.charAt(p) - 'A';
        if (cur.childs[x] == null)
            cur.childs[x] = new TreeNode();
        dfs(cur.childs[x], s, p + 1);
    }

    static int max = 0;

    public static int dfs2(TreeNode rt, int d) {
        int cur = rt.cnt;
        for (int i = 0; i < 26; i++) {
            if (rt.childs[i] != null) {
                cur += dfs2(rt.childs[i], d + 1);
            }
        }
        max += d * (cur / K);
        return cur % K;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        int[] ans = new int[N + 1];
        for (int cases = 1; cases <= N; cases++) {
            n = scanner.nextInt();
            K = scanner.nextInt();
            scanner.nextLine();
            TreeNode root = new TreeNode();
            for (int i = 0; i < n; i++) {
                dfs(root, scanner.nextLine(), 0);
            }
            max = 0;
            dfs2(root, 0);
            ans[cases] = max;
        }
        for (int i = 1; i <= N; i++) {
            System.out.printf("Case #%d: %d\n", i, ans[i]);
        }
    }
}
//            maxdif = Integer.MIN_VALUE;
//            for (int i = 1; i < n; i++) {
//                difs[i - 1] = Math.abs(ps[i] - ps[i - 1]);
//                maxdif = Math.max(maxdif, difs[i - 1]);
//            }
//            ans[cases] = solve3();

//    static int[] ps;
//    static int[] difs;
//    static int maxdif;
//    public static int solve3() {
//        int l = 1;
//        int r = maxdif;
//        while (l < r) {
//            int mid = l + r >> 1;
//            if (check(mid))
//                r = mid;
//            else
//                l = mid + 1;
//        }
//        return l;
//    }
//
//    public static boolean check(int maxdif) {
//        int cnt = 0;
//        for (int dif : difs) {
//            if (dif <= maxdif)
//                continue;
//            else {
//                int cc = dif / maxdif;
//                if (cc * maxdif >= dif)
//                    cnt += cc - 1;
//                else {
//                    cnt += cc;
//                }
//            }
//        }
//
//        return cnt <= K;
//    }


//   static int p;
//    static int[][] stacks;
//    static int[][] presums;
//    public static void compute() {
//        presums = new int[n][K + 1];
//        for (int i = 0; i < n; i++)
//            for (int j = 1; j <= K; j++)
//                presums[i][j] = presums[i][j - 1] + stacks[i][j - 1];
//    }
//
//    public static int solve2() {
//        int[][][] dp = new int[n+1][K+1][p+1];
//        int max = Integer.MIN_VALUE;
//        for (int i = 1; i <= n; i++)
//            for (int j = 0; j <= K; j++) {
//                for (int k = j; k <= p; k++) {
//                    for (int t = 0; t <=k - j&&t<=K; t++)
//                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][t][k-j]);
//                    dp[i][j][k] += presums[i - 1][j];
//                }
//                if (i == n)
//                    max = Math.max(dp[n][j][p], max);
//            }
//        return max;
//    }

//    public static int solve() {
//        Arrays.sort(ps);
//        int cnt = 0;
//        int rest = p;
//        for (int i = 0; i < n; i++) {
//            rest -= ps[i];
//            if (rest >= 0) {
//                cnt++;
//            } else
//                break;
//        }
//        return cnt;
//    }

//    private static int solve() {
//        Arrays.sort(ps);
//        int[] psum = new int[n + 1];
//        for (int i = 1; i <= n; i++)
//            psum[i] = psum[i - 1] + ps[i - 1];
//        int min = Integer.MAX_VALUE;
//        for (int i = p; i <= n; i++) {
//            int cur = p * ps[i - 1] - (psum[i] - psum[i - p]);
//            min = Math.min(cur, min);
//        }
//        return min;
//    }
//}

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int N = scanner.nextInt();
//        scanner.nextLine();
//        int[] ans = new int[N + 1];
//        for (int cases = 1; cases <= N; cases++) {
//            n = scanner.nextInt();
//            m = scanner.nextInt();
//            scanner.nextLine();
//            g = new String[n];
//            for (int i = 0; i < n; i++) {
//                g[i] = scanner.nextLine();
//            }
//
//            ans[cases] = packSolver();
//        }
//
//        for (int cases = 1; cases <= N; cases++) {
//            // \n  newline  for unix-os  \r\n for windows
//            System.out.printf("Case #%d: %d\n", cases, ans[cases]);
//        }
//    }
//
//    static int[] dir = {0, -1, 0, 1, 0};
//
//    private static int packSolver() {
//        int l = 0;
//        int r = n + m;
//        while (l < r) {
//            int mid = (l + r) >> 1;
//            if (check(mid)) {
//                r = mid;
//            } else
//                l = mid + 1;
//        }
//        return l;
//    }
//
//    public static boolean check(int k) {
//        int[][] matrix = new int[n][m];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < m; j++) {
//                if (g[i].charAt(j) == '1')
//                    matrix[i][j] = 1;
//            }
//
//        visited = new boolean[n][m];
//
//        Queue<int[]> queue = new LinkedList<>();
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < m; j++) {
//                if (matrix[i][j] == 1) {
//                    queue.add(new int[]{i, j});
//                    visited[i][j] = true;
//                }
//            }
//
//        int level = 0;
//        while (!queue.isEmpty() && level < k) {
//            int size = queue.size();
//            while (size > 0) {
//                size--;
//                int[] cur = queue.poll();
//                int cx = cur[0];
//                int cy = cur[1];
//                for (int i = 0; i < 4; i++) {
//                    int nx = cx + dir[i];
//                    int ny = cy + dir[i + 1];
//                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue;
//                    visited[nx][ny] = true;
//                    queue.add(new int[]{nx, ny});
//                }
//            }
//            level++;
//        }
//
//        int max_sum = Integer.MIN_VALUE;
//        int max_sub = Integer.MIN_VALUE;
//        int min_sum = Integer.MAX_VALUE;
//        int min_sub = Integer.MAX_VALUE;
//
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < m; j++) {
//                if (!visited[i][j]) {
//                    max_sub = Math.max(i - j, max_sub);
//                    max_sum = Math.max(i + j, max_sum);
//                    min_sub = Math.min(i - j, min_sub);
//                    min_sum = Math.min(i + j, min_sum);
//                }
//            }
//
//        if (min_sum == Integer.MAX_VALUE)
//            return true;
//
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < m; j++) {
//                if (matrix[i][j] != 1) {
//                    int min = Math.max(Math.abs(i + j - max_sum), Math.abs(i + j - min_sum));
//                    int max = Math.max(Math.abs(i - j - max_sub), Math.abs(i - j - min_sub));
//                    if (Math.max(min, max) <= k)
//                        return true;
//                }
//            }
//        return false;
//    }
//    public static int solve(int n, int p, int[] skills) {
//        Arrays.sort(skills);
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        int sum = 0;
//        int min = Integer.MIN_VALUE;
//        for (int skill : skills) {
//            sum += skill;
//            pq.add(skill);
//            if (pq.size() > p) {
//                sum -= pq.poll();
//            }
//            if (pq.size() == p) {
//                int rest = p * skill - sum;
//                min = Math.min(min, rest);
//            }
//        }
//        return min;
//    }


