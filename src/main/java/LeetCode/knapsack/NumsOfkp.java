package LeetCode.knapsack;

import java.util.Arrays;
import java.util.Scanner;

public class NumsOfkp {


    static int N;
    static int[] f = new int[N];
    static long[] cnt = new long[N];
    static int[] vs, ws;
    static int m;
    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        Arrays.fill(cnt, 1);
        for (int i = 1; i <= n; i++) {
            sc.nextLine();
            vs[i] = sc.nextInt();
            ws[i] = sc.nextInt();
            for (int v = m; v >= vs[i]; v--) {
                int value = f[v - vs[i]] + ws[i];
                if (value > f[v]) {
                    f[v] = value;
                    cnt[v] = cnt[v - vs[i]] % mod;
                } else if (value == f[v])
                    cnt[v] = (cnt[v] + cnt[v - vs[i]]) % mod;
            }
        }

        System.out.println(cnt[m] % mod);
    }

}
