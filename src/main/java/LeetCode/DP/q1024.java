package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.HashMap;

public class q1024 {
    public int videoStitching(int[][] clips, int T) {
        int n = clips.length;
        // dp[i][j]: dp[]
        HashMap<Integer, Integer> hm = new HashMap<>();
        int res = helper(clips, T, 0, hm);
        return res >= Integer.MAX_VALUE / 2 ? -1 : res;
    }

    public int helper(int[][] clips, int T, int left, HashMap<Integer, Integer> hm) {
        if (left >= T)
            return 0;

        if (hm.containsKey(left))
            return hm.get(left);

        int minsteps = Integer.MAX_VALUE / 2;
        for (int i = 0; i < clips.length; i++) {
            if (clips[i][1] <= left)
                continue;
            if (clips[i][0] <= left) {
                minsteps = Math.min(minsteps, 1 + helper(clips, T, clips[i][1], hm));
            }
        }
        hm.put(left, minsteps);
        return minsteps == Integer.MAX_VALUE ? -1 : minsteps;
    }

    public int dp(int[][] clips, int T) {
        int n = clips.length;
        int[][] dp = new int[n + 1][T + 1];

        for (int t = 1; t <= T; t++) {
            int left = clips[t - 1][0];
            int right = clips[t - 1][1];
            for (int i = 1; i <= n; i++) {
                if (i <= right)
                    dp[t][i] = 1;
            }
        }
        return -1;
    }


}
