package LeetCode.DP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class q1048 {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.length() - t1.length();
            }
        });
        int n = words.length;
        HashMap<String, Integer> hm = new HashMap<>();
        int[] dp = new int[n];
        dp[0] = 1;
        hm.put(words[0], 0);
        int max = 1;
        for (int i = 1; i < n; i++) {
            String cur = words[i];
            dp[i] = 1;
            if (cur.length() > 1) {
                for (int j = 1; j < cur.length() - 1; j++) {
                    String pre = cur.substring(0, j) + cur.substring(j + 1);
                    if (hm.containsKey(pre)) {
                        int p = hm.get(pre);
                        dp[i] = Math.max(dp[i], dp[p] + 1);
                    }
                }
                String prefix = cur.substring(1);
                if (hm.containsKey(prefix)) {
                    int p = hm.get(prefix);
                    dp[i] = Math.max(dp[i], dp[p] + 1);
                }
                String suffix = cur.substring(0, cur.length() - 1);
                if (hm.containsKey(suffix)) {
                    int p = hm.get(suffix);
                    dp[i] = Math.max(dp[i], dp[p] + 1);
                }
            }
            max = Math.max(dp[i], max);
            hm.put(cur, i);
        }
        return max;
    }
}

