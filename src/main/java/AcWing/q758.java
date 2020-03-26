package AcWing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class q758 {
    static int N;
    static long[][] dp;
    static int[] colors;
    static int[] parents;
    static HashMap<Integer, List<Integer>> hm;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        scanner.nextLine();
        parents = new int[N];
        colors = new int[N];
        parents[0] = 0;
        for (int i = 1; i < N; i++)
            parents[i] = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < N; i++)
            colors[i] = scanner.nextInt();
        System.out.println(solve());
    }

    public static int solve() {
        dp = new long[N][2];
        int root = 0;
        hm = new HashMap<>();
        for (int i = 1; i < N; i++) {
            int par = parents[i];
            if (!hm.containsKey(par))
                hm.put(par, new LinkedList<>());
            hm.get(par).add(i);
        }
        dfs(0);
        return (int) dp[0][1] % mod;
    }

    static int mod = (int) 1e9 + 7;

    public static void dfs(int root) {
        if (!hm.containsKey(root)) {
            if (colors[root] == 0)
                dp[root][1] = 1;
            else
                dp[root][0] = 1;
            return;
        }

        for (int child : hm.get(root))
            dfs(child);

        int k = hm.get(root).size();

        if (colors[root] == 0) {
            dp[root][0] = 0;
            long t = 1;
            for (int child : hm.get(root))
                t = (t % mod * (dp[child][0] + dp[child][1]) % mod) % mod;

            dp[root][1] = t;
        } else {

            long t = 1l;
            for (int child : hm.get(root))
                t = (t * (dp[child][0] + dp[child][1]) % mod) % mod;

            dp[root][0] = t;

            long[] premul = new long[k + 2];
            long[] sufmul = new long[k + 2];
            premul[0] = 1;
            sufmul[k + 1] = 1;

            for (int i = 1; i <= k; i++) {
                int cur = hm.get(root).get(i - 1);
                premul[i] = (premul[i - 1] % mod * (dp[cur][1] + dp[cur][0]) % mod) % mod;
            }
            System.out.println(premul[k]);
            for (int i = k; i >= 1; i--) {
                int cur = hm.get(root).get(i - 1);
                sufmul[i] = (sufmul[i + 1] % mod * (dp[cur][1] + dp[cur][0]) % mod) % mod;
            }

            t = 0l;
            for (int i = 1; i <= k; i++) {
                int cur = hm.get(root).get(i - 1);
                t = (t + dp[cur][1] % mod * (premul[i - 1] % mod * sufmul[i + 1] % mod)) % mod;
            }
            dp[root][1] = t;
        }
    }
}

