package LeetCode.DP;

public class q1025 {


    public boolean divisorGameDP(int N) {
        if (N == 1)
            return false;

        boolean[] dp = new boolean[N + 1];
        dp[1] = false;
        dp[2] = true;
        for (int i = 3; i <= N; i++)
            for (int j = 1; !dp[i] && j < i; j++)
                if (i % j == 0 && !dp[i - j])
                    dp[i] = true;
        return dp[N];

    }

    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N + 1];
        return helper(N);
    }

    public boolean helper(int N) {
        if (N <= 1)
            return false;
        if (N == 2)
            return true;
        for (int i = 1; i < N; i++)
            if (N % i == 0 && !helper(N - i))
                return true;
        return false;
    }
}
