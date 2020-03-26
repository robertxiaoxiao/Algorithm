package LeetCode.DP;

public class q887 {


    private int[][] memo;
    /*
           dp[i][j] : the min steps to find the floor with i eggs in j floors
        for(int k=1;k<j;k++)
      dp[i][j] = min(1+max(dp[i][k-1],dp[i][n-k]))
     */


    public int superEggDrop(int K, int N) {
        memo = new int[K + 1][N + 1];
        return helper(K, N);
    }

    private int helper(int K, int N) {
        if (N <= 1) return N;
        if (K == 1) return N;
        if (memo[K][N] > 0) return memo[K][N];
        int lo = 1, hi = N, result = N;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int left = helper(K - 1, mid - 1);
            int right = helper(K, N - mid);
            result = Math.min(result, Math.max(left, right) + 1);
            if (left == right) break;
            else if (left < right) lo = mid + 1;
            else hi = mid - 1;
        }
        memo[K][N] = result;
        return result;
    }

    public int superEggDropDP(int K, int N) {
        /*
firstly, if we have k eggs and s steps to detect a building with Q(k, s) floors,
secondly, we use 1 egg and 1 step to detect one floor,
if egg break, we can use (k-1) eggs and (s-1) to
detect with Q(k-1, s-1),if egg isn't broken, we can use
k eggs and (s-1) step to detech with Q(k, s-1),
So, Q(k, s) = 1 + Q(k, s-1) + Q(k-1, s-1);
dp[i][s] is max floors we can use i eggs and s step to detect.
         */
        int[][] dp = new int[K + 1][N + 1];
        // dp[k][m] :using k eggs with  m move to check the Max floor
        int m = 0;
        dp[0][0] = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + 1 + dp[k - 1][m - 1];
        }
        return m;
    }

    public int superEggDropTopDownDP(int K, int N) {
        // opt[moves][eggs] := using m moves and e eggs, how many floors can we check?
        int[][] opt = new int[N + 1][K + 1];

        for (int m = 1; m <= N; ++m) {
            for (int e = 1; e <= K; ++e) {
                opt[m][e] = opt[m - 1][e - 1] + opt[m - 1][e] + 1;
                // have we checked all the floors?
                if (opt[m][e] >= N) {
                    return m;
                }
            }
        }
        return -1; // we'll never get here
    }

}
