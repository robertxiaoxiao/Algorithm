package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/19/019
 * @description:
 */

public class q790 {
    static int MOD = 1000000007;

    public static int numTilings(int N) {

        int max = 1001;
        //dp1  end with x
        //              x
        //              i
        // dp2  end with  x
        //                x x
        //                  i
        // dp3  end with  x x
        //                x
        //                  i

        if (N == 1)
            return 1;

        int[] A = new int[N + 1];
        int[] B = new int[N + 1];
        A[1] = 1;
        A[2] = 2;
        B[2] = 1;

        for (int i = 3; i <= N; i++) {
            // get rid of overflow
            A[i] = ((A[i - 1] + A[i - 2]) % MOD + (2 * B[i - 1]) % MOD) % MOD;
            B[i] = (A[i - 2] + B[i - 1]) % MOD;
        }

        return A[N] % MOD;
    }

    public int numTilingsCompress(int N) {
        //dp1  end with x
        //              x
        //              i
        // dp2  end with  x
        //                x x
        //                  i
        // dp3  end with  x x
        //                x
        //                  i
        if (N == 1)
            return 1;
        int firA = 1;
        int secA = 2;
        int firB = 1;
        int nextA = 0;
        int nextB = 0;
        for (int i = 3; i <= N; i++) {
            nextA = ((firA + secA) % MOD + (2 * firB) % MOD) % MOD;
            nextB = (firA + firB) % MOD;
            firA = secA;
            secA = nextA;
            firB = nextB;
        }
        return secA;
    }

    public static void main(String[] args) {
        System.out.println(MOD);
        System.out.println(numTilings(30));
        System.out.println(MOD);
    }

}
