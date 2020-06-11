package leetcodeTest;

import com.google.common.cache.AbstractLoadingCache;

import java.util.*;

public class Week30 {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                max = Math.max(max, (nums[i] - 1) * (nums[j] - 1));
        return max;
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        int maxl = 0;
        int maxw = 0;
        int n = horizontalCuts.length;
        int m = verticalCuts.length;
        for (int i = 0; i < n; i++) {
            if (i == 0)
                maxl = horizontalCuts[i];
            else
                maxl = Math.max(maxl, horizontalCuts[i] - horizontalCuts[i - 1]);
        }
        maxl = Math.max(maxl, h - horizontalCuts[n - 1]);

        for (int i = 0; i < m; i++) {
            if (i == 0)
                maxw = verticalCuts[i];
            else
                maxw = Math.max(maxw, verticalCuts[i] - verticalCuts[i - 1]);
        }
        maxw = Math.max(maxw, h - verticalCuts[m - 1]);

        long res = (long) maxl * (long) maxw;
        return (int) (res % (1e9 + 7));

    }

    public int minReorder(int n, int[][] connections) {
        HashMap<Integer, List<int[]>> hm = new HashMap<>();
        for (int[] c : connections) {
            int f = c[0];
            int t = c[1];
            if (!hm.containsKey(f))
                hm.put(f, new LinkedList<>());
            if (!hm.containsKey(t))
                hm.put(t, new LinkedList<>());
            hm.get(f).add(new int[]{t, 1});
            hm.get(t).add(new int[]{f, 0});
        }
        return dfs(0, -1, hm);
    }

    public int dfs(int cur, int par, HashMap<Integer, List<int[]>> hm) {
        if (!hm.containsKey(cur))
            return 0;

        int ans = 0;
        for (int[] c : hm.get(cur)) {
            int next = c[0];
            int dir = c[1];
            if (next == par) continue;
            ans += dfs(next, cur, hm) + dir;
        }
        return ans;
    }

    public double C(int m, int n) {
        if (m < n)
            return 0;
        if (m == n)
            return 1.0;

        double ret = 1.0;
        for (int i = m; i > n; i--)
            ret *= i;
        for (int i = 1; i <= m - n; i++)
            ret /= i;

        return ret;
    }

    public double getProbability(int[] balls) {

        int cnt = 0;
        for (int i : balls)
            cnt += i;
        int n = cnt / 2;
        int m = balls.length;
        double[][][][] dp = new double[m + 1][n + 1][m + 1][m + 1];
        // 前m 中颜色中 左边盒子数量为n，且 左边和右边盒子中颜色数分别是 p,和q的概率
        dp[0][0][0][0] = 1.0;
        int sum = 0;
        for (int i = 1; i <= m; i++) {
            int curballs = balls[i - 1];
            for (int l = 0; l <= n; l++)
                for (int p = 0; p <= m; p++)
                    for (int q = 0; q <= m; q++) {
                        double tot = 0;
                        for (int cl = 0; cl <= curballs; cl++) {
                            if (cl + l <= n && sum - l + (curballs - cl) <= n)
                                tot += C(n - l, cl) * C(n - (sum - l), curballs - cl);
                        }

                        for (int cl = 0; cl <= curballs; cl++) {
                            if (cl + l <= n && sum - l + (curballs - cl) <= n) {
                                int np = p + (cl > 0 ? 1 : 0);
                                int nq = q + ((curballs - cl) > 0 ? 1 : 0);
                                // 在总和为n当前选择cl个放入的数量  ：  从n-l个格子中选择cl个位置
                                double v = C(n - l, cl) * C(n - (sum - l), curballs - cl) / tot;
                                if (np <= m && nq <= m)
                                    dp[i][l + cl][np][nq] += dp[i - 1][l][p][q] * v;
                            }
                        }
                    }
            sum += curballs;
        }

        double ans = 0;
        for (int i = 0; i <= m; i++)
            ans += dp[m][n][i][i];
        return ans;
    }


    // biweekly

    public boolean hasAllCodes(String s, int k) {
        int t = 0;
        int n = s.length();
        int lim = 1 << k;
        boolean[] visited = new boolean[(int) lim];
        for (int i = 0; i < k && i < n; i++)
            t = t * 2 + (s.charAt(i) - '0');
        if (t < lim)
            visited[t] = true;

        for (int i = k + 1; i < n; i++) {
            int nt = t * 2 % lim;
            nt = nt + (s.charAt(i) - '0');
            visited[nt] = true;
        }
        for (boolean b : visited)
            if (!b)
                return false;
        return true;
    }

    // Floyd
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {

        boolean[][] connected = new boolean[n][n];
        for (int[] p : prerequisites)
            connected[p[0]][p[1]] = true;

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    if (connected[i][k] && connected[k][j])
                        connected[i][j] = true;
                }

        List<Boolean> res = new LinkedList<>();
        for (int[] q : queries) {
            res.add(connected[q[0]][q[1]]);
        }
        return res;
    }

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][m];
        for (int t = 0; t < n; t++)
            for (int l = 0; l < m; l++)
                for (int r = 0; r < m; r++)
                    dp[t][l][r] = -1;
        dp[0][0][m - 1] = grid[0][0] + grid[0][m - 1];
        for (int t = 0; t < n - 1; t++)
            for (int l = 0; l < m; l++)
                for (int r = 0; r < m; r++) {
                    // NOTICE : INIT VALUE
                    if (dp[t][l][r] == -1)
                        continue;
                    for (int ld = -1; ld <= 1; ld++) {
                        for (int rd = -1; rd <= 1; rd++) {
                            int nl = l + ld;
                            int nr = r + rd;
                            if (nl < 0 || nl >= m || nr < 0 || nr >= m) continue;
                            int gets = grid[t + 1][nl] + grid[t + 1][nr];
                            if (nl == nr) gets -= grid[t + 1][nl];
                            dp[t + 1][nl][nr] = Math.max(dp[t + 1][nl][nr], dp[t][l][r] + gets);
                        }
                    }
                }

        int max = 0;
        for (int l = 0; l < m; l++)
            for (int r = 0; r < m; r++) {
                max = Math.max(max, dp[n - 1][l][r]);
            }

        return max;
    }
}
