package LeetCode.DP.数位DP;

public class Ac1086 {


    static class Node {
        int cnt;
        int lsum;
        int l_sq;
    }

    static int N = 20;
    static int P = (int) 1e9 + 7;
    int[] pow10mod7 = new int[N];
    int[] pow10modp = new int[N];
    static Node[][][][] dp = new Node[N][10][7][7];

    public static int mod(long x, long p) {
        return (int) ((int) (x % p + p) % p);
    }

    public static void init() {

        for (int i = 0; i <= 9; i++) {
            if (i == 7)
                continue;
            Node cur = new Node();
            cur.cnt = 1;
            cur.lsum = i;
            cur.l_sq = i * i;
            dp[1][i][i % 7][i % 7] = cur;
        }
        long power = 10;
        for (int i = 2; i < N; i++, power *= 10) {
            for (int j = 0; j < 10; j++) //cur pos
            {
                if (j == 7)
                    continue;

                for (int k = 0; k < 10; k++) // the prepos
                {
                    if (k == 7)
                        continue;
                    for (int a = 0; a < 7; a++)
                        for (int b = 0; b < 7; b++) {
                               /*
                      (jA1)+(jA2)+(jA3)+...+(jAt)
                    = (j*10^{i-1})*t + (A1+A2+A3+...+At)

                      (jA1)^2+(jA2)^2+(jA3)^2+...+(jAt)^2
                    = ((j*10^{i-1})^2)*t +
                      2*(j*10^{i-1})*(A1+A2+...+At) +
                      (A1^2+A2^2+...+At^2)
                    */
                            long pj = power * j;
                            Node t1 = dp[i][j][mod(a + pj, 7)][mod(b + j, 7)];
                            Node t2 = dp[i - 1][k][a][b];
                            t1.cnt = mod(t2.cnt + t1.cnt, P);
                            t1.lsum = mod(pj * t1.cnt + t2.lsum, P);
                            t1.l_sq = mod(mod(pj * pj, P) + mod(pj * 2 * t2.lsum, P) + mod(t2.l_sq, P), P);
                        }
                }
            }
        }


    }


}
