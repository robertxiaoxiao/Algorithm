package LeetCode.DP;

public class q983 {

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        dp[0] = 0;
        boolean[] visited = new boolean[366];
        for (int i : days)
            visited[i] = true;
        int pd = costs[0];
        int pw = costs[1];
        int pm = costs[2];
        int md = Math.min(pd, pw);
        md = Math.min(md, pm);
        for (int i = 1; i <= 365; i++) {
            if (visited[i])
                dp[i] = dp[i - 1] + md;
            else
                dp[i] = dp[i - 1];

            if (i >= 7)
                dp[i] = Math.min(dp[i], dp[i - 7] + pw);
            if (i >= 30)
                dp[i] = Math.min(dp[i], dp[i - 30] + pm);
        }
        return dp[365];
    }
}
