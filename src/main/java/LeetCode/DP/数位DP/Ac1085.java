package LeetCode.DP.数位DP;


import java.util.Arrays;
import java.util.Scanner;

public class Ac1085 {

    static int N = 10;
    static int a[] = new int[N];
    static long[][] f;

    public static long getNums(int m) {
        a[0] = 0;
        int k = 0;
        while (m != 0) {
            a[++k] = m % 10;
            m = m / 10;
        }
        return dfs(k, true, 0);
    }

    public static long dfs(int i, boolean equal, int last) {
        if (i == 0)
            return 1;

        if (!equal && f[i][last] != -1)
            return f[i][last];

        int lim = equal ? a[i] : 9;
        int sum = 0;
        for (int k = 0; k <= lim; k++) {
            if (k == 4)
                continue;
            if (last == 6 && k == 2)
                continue;
            sum += dfs(i - 1, k == lim && equal, k);
        }

        if (!equal)
            f[i][last] = sum;
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            if (n == 0 && n == m)
                break;

            f = new long[N][N];
            for (int i = 0; i < N; i++)
                Arrays.fill(f[i], -1);
            long km = getNums(m);
            long kn = getNums(n - 1);
            int ans = (int) (km - kn);
            sb.append(String.format("%d\n", ans));
        }

        System.out.print(sb.toString());
    }
}
