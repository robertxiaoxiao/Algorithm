package leetcodeTest.Week13;/*
 * @author: Robert
 * @date:  2020/2/2/002
 * @description:
 */

import java.util.*;

public class q4 {


    public int maxJumpsRec(int[] arr, int d) {
        int max = -1;
        int n = arr.length;
        int[] mem = new int[n];
        for (int i = 0; i < n; i++) {
            max = Math.max(dfs(arr, d, 0, mem), max);
        }
        return max;
    }

    public int dfs(int[] arr, int d, int cur, int[] mem) {
        if (mem[cur] != 0)
            return mem[cur];
        int ans = 1;
        for (int i = cur - 1; i >= Math.max(0, cur - d) && arr[i] < arr[cur]; i--) {
            ans = Math.max(ans, dfs(arr, d, i, mem) + 1);
        }

        for (int i = cur + 1; i <= Math.min(cur + d, arr.length - 1) && arr[i] < arr[cur]; i++) {
            ans = Math.max(ans, dfs(arr, d, i, mem) + 1);
        }
        return mem[cur] = ans;
    }

    public int maxJumps(int[] arr, int d) {
        List<int[]> list = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++)
            list.add(new int[]{arr[i], i});
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // initialization
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int[] key : list) {
            int cur = key[1];
            for (int i = cur - 1; i >= Math.max(0, cur - d) && arr[i] < arr[cur]; i--) {
                dp[cur] = Math.max(dp[cur], dp[i] + 1);
            }
            for (int i = cur + 1; i <= Math.min(cur + d, arr.length - 1) && arr[i] < arr[cur]; i++) {
                dp[cur] = Math.max(dp[cur], dp[i] + 1);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, dp[i]);
        return max;
    }

}
