package LeetCode.DP;

public class q1049 {

    public int lastStoneWeightII(int[] stones) {

        /*
        Same problem as:
        Divide all numbers into two groups,
        what is the minimum difference between the sum of two groups.
        Now it's a easy classic knapsack problem.

        // get get the biggest stone in last smash
           ans: the max stone -the minRes of remaining stones
        */

        boolean[] dp = new boolean[1501];
        //dp[i]: whether the sum of  the small group  can reach i;
        dp[0] = true;
        int sum = 0;
        for (int a : stones) {
            sum += a;
            for (int i = Math.min(1500, sum); i >= a; i--)
                dp[i] |= dp[i - a];
        }

        for (int i = sum / 2; i >= 0; i--)
            if (dp[i])
                return sum - i - i;

        return 0;
    }


}
