package LeetCode.DP;

import java.util.HashSet;

public class q467 {

    public int findSubstringInWraproundString(String p) {
        int n = p.length();
        if (n == 0)
            return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        int[] max = new int[26];
        HashSet<Integer> single = new HashSet<>();
        single.add(p.charAt(0) - 'a');
        max[p.charAt(0) - 'a'] = 1;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int pre = p.charAt(i - 1) - 'a';
            int cur = p.charAt(i) - 'a';

            if ((pre + 1) % 26 == cur)
                dp[i] = dp[i - 1];

            dp[i] += 1;
            max[cur] = Math.max(max[cur], dp[i]);
        }

        for (int i : max)
            ans += i;
        return ans;
    }
}
