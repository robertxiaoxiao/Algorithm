package LeetCode.knapsack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DAGTree {

    static int N = 110;
    static HashMap<Integer, List<Integer>> graph;
    static int[][] f = new int[N][N]; //  f[i][v] : maxvalue of selecting node i and volume<=v
    // f[u][v] =f[u][k] +f[son][v-k]
    static int[] vs = new int[N];
    static int[] ws = new int[N];
    static int m;

    public static void dfs(int u) {

        if (graph.containsKey(u)) {
            for (int i : graph.get(u)) {
                dfs(i);
                for (int j = m - vs[u]; j >= 0; j--) {
                    for (int k = 0; k <= j; k++)
                        // knapsack group strategy : f[v] =max(f[v],f[v-vi]+wi);
                        f[u][j] = Math.max(f[u][j], f[u][j - k] + f[i][k]);
                }
            }
        }

        for (int v = m; v >= vs[u]; v--)
            f[u][v] = f[u][v - vs[u]] + ws[u];

        for (int i = 0; i < vs[u]; i++) {
            f[u][i] = 0;
        }

    }

    public static void main(String[] args) {
        graph = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        int root = -1;
        for (int i = 1; i <= n; i++) {
            sc.nextLine();
            vs[i] = sc.nextInt();
            ws[i] = sc.nextInt();
            int p = sc.nextInt();
            if (p == -1)
                root = i;
            else {
                if (!graph.containsKey(p))
                    graph.put(p, new LinkedList<>());
                graph.get(p).add(i);
            }
        }
        dfs(root);
        System.out.println(f[root][m]);
    }
}
