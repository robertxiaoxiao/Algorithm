package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/3/003
 * @description:
 */

import java.util.HashMap;

public class q546 {

    int[][][] dp;

    public static int[] update(int[] boxes, int i) {
        int n = boxes.length;
        int r = i + 1;
        int l = i - 1;
        while (r < n && boxes[r] == boxes[i])
            r++;

        while (l >= 0 && boxes[l] == boxes[i])
            l--;

        int newn = n - (r - l - 1);
        int[] newarr = new int[newn];
        int j = 0;
        int k = 0;
        while (j < newn && k < n) {
            if (k <= l || k >= r)
                newarr[j++] = boxes[k];
            k++;
        }
        return newarr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        for (int i : update(arr, 2))
            System.out.print(i + "  ");
    }

//    public int dp(int[] boxes) {
//
//        //dp[i][j][k] :  boxes[i][j]  ending with k same as boxes[j];
//        int n = boxes.length;
//        int[][][] dp = new int[n][n][n];
//
//        int[] suffix = new int[n];
//        for (int i = n - 2; i >= 0; i--)
//            suffix[i] = suffix[i + 1] + (boxes[i] == boxes[i + 1] ? 1 : 0);
//
//        for (int len = 1; len <= n; len++)
//            for (int i = 0; i + len - 1 < n; i++) {
//                int j = i + len - 1;
//                for(int k=0;k<= suffix[j];k++) {
//                        dp[i][j][k] = j-1>=i?dp[i][j - 1][0]:0 + (k + 1) * (k + 1);
//                    for (int cut = i; cut < j; cut++) {
//                        if (boxes[cut] == boxes[j])
//                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i][cut][k + 1] + dp[cut + 1][j - 1][0]);
//                    }
//                }
//            }
//        return dp[0][n - 1][0];
//    }

    public int removeBoxes(int[] boxes) {
        HashMap<String, Integer> mem = new HashMap<>();
        int n = boxes.length;
        dp = new int[n][n][n];
        return dfs(boxes, 0, n - 1, 0);
    }

    public int dfs(int[] boxes, int l, int r, int k) {
        if (l > r)
            return 0;
        if (dp[l][r][k] != 0)
            return dp[l][r][k];

        //'22222222'
        int j = r - 1;
        while (j >= l && boxes[j] == boxes[r]) {
            j--;
            k++;
        }
        r = j + 1;

        dp[l][r][k] = dfs(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int cut = l; cut < r; cut++) {
            if (boxes[cut] == boxes[r])
                dp[l][r][k] = Math.max(dp[l][r][k], dfs(boxes, l, cut, k + 1) + dfs(boxes, cut + 1, r - 1, 0));
        }
        return dp[l][r][k];
    }

    public String arrtostr(int[] boxes) {
        StringBuffer sb = new StringBuffer();
        for (int num : boxes)
            sb.append(num + '-');
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public int helper(int[] boxes, HashMap<String, Integer> mem) {
        if (boxes.length == 0)
            return 0;

        String cur = arrtostr(boxes);
        if (mem.containsKey(cur))
            return mem.get(cur);

        int n = boxes.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && boxes[i] == boxes[i - 1])
                continue;
            int[] narr = update(boxes, i);
            int k = n - narr.length;
            ans = Math.max(ans, k * k + helper(narr, mem));
        }
        mem.put(cur, ans);
        return ans;
    }

}
