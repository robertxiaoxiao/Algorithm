package LeetCode.knapsack;

import java.util.Arrays;
import java.util.Scanner;

public class AlphSelection {

    static int N = 1010;
    static int[][] f = new int[N][N];
    static int[] ws, vs;
    static int m;
    static int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        ws = new int[N];
        vs = new int[N];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        f[0][0] = 0;
        Arrays.fill(f[0], Integer.MIN_VALUE);
        // f[i][j] : max value of i-end at volume with j
        for (int i = 1; i <= n; i++) {
            sc.nextLine();
            vs[i] = sc.nextInt();
            ws[i] = sc.nextInt();
        }

        for (int i = n; i >= 1; i--) {
            for (int k = 0; k <= m; k++) {
                f[i][k] = f[i + 1][k];
                if (k >= vs[i])
                    f[i][k] = Math.max(f[i][k], f[i + 1][k - vs[i]] + ws[i]);
            }
        }

        StringBuffer sb = new StringBuffer();
        int curv = m;
        for (int i = 1; i <= n; i++) {
            if (curv - vs[i] >= 0 && f[i][curv] == f[i + 1][curv - vs[i]] + ws[i]) {
                curv -= vs[i];
                sb.append(i).append(' ');
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }

}
