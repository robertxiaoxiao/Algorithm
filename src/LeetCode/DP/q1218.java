package LeetCode.DP;

import java.util.HashMap;

public class q1218 {

    /*
        using hashmap to record the dp state
     */
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(arr[0], 0);
        int max = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            int pre = arr[i] - difference;
            if (hm.containsKey(pre))
                dp[i] = dp[hm.get(pre)] + 1;
            hm.put(arr[i], i);
            System.out.printf("%d %d ", pre, dp[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int longestSubsequenceEfficient(int[] arr, int diff) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int a : arr) {
            if (map.containsKey(a - diff)) {
                map.put(a, map.get(a - diff) + 1);
            } else {
                map.put(a, 1);
            }
            max = Math.max(map.get(a), max);
        }
        return max;
    }
}
