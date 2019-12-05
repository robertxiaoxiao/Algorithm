package leetcodeTest.Week2;/*
 * @author:
 * @date:  2019/11/17/017
 * @description:
 */

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class q3 {
    int max;

    /*
      dp[i] :  数字累加为sum%3==i的最大数字和
      if (nums[i]%3==k)
            dp[k]=max(dp(k),nums[i]+dp[3-k])
     */

    public int maxSumDivThreeI(int[] nums) {
        max = Integer.MIN_VALUE;
        helper(nums, 0, 0);
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    public int dp(int[] nums) {
        int[] dp = new int[3];
        for (int i = 0; i < nums.length; i++) {
            int mod = nums[i] % 3;
            int a = dp[(3 + 0 - mod) % 3];
            int b = dp[(3 + 1 - mod) % 3];
            int c = dp[(3 + 2 - mod) % 3];

            if (mod == 0)
                dp[0] = Math.max(dp[0], a + nums[i]);
            if (mod == 1)
                dp[1] = Math.max(dp[1], b + nums[i]);
            if (mod == 2)
                dp[2] = Math.max(dp[2], c + nums[i]);
        }
        return dp[0];
    }

    public int maxSumDivThree(int[] nums) {
        int ans = 0;
        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            ans += nums[i];
            if (nums[i] % 3 == 1)
                list1.add(nums[i]);
            if (nums[i] % 3 == 2)
                list2.add(nums[i]);
        }

        if (ans % 3 == 0)
            return ans;
        Collections.sort(list1);
        Collections.sort(list2);
        if (ans % 3 == 1) {
            if (list1.size() >= 1)
                ans = ans - list1.get(0);
            if (list2.size() >= 2)
                ans = Math.max(ans, ans - list2.get(0) - list2.get(0));
        } else if (ans % 3 == 2) {
            if (list2.size() >= 1)
                ans = ans - list2.get(0);
            if (list1.size() >= 2)
                ans = Math.max(ans, ans - list1.get(0) - list1.get(0));
        }
        return ans;
    }

    public void helper(int[] nums, int cur, int curMax) {
        if (cur == nums.length && curMax % 3 == 0 && curMax > max) {
            max = curMax;
            return;
        }

        int temp = nums[cur];
        if (temp <= 0)
            helper(nums, cur + 1, curMax);

        if (temp % 3 == 0)
            helper(nums, cur + 1, curMax + temp);
        else {
            helper(nums, cur + 1, curMax + temp);
            helper(nums, cur + 1, curMax);
        }
    }
}
