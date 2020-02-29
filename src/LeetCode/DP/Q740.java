package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/19/019
 * @description:
 */

public class Q740 {

    public int deleteAndEarn(int[] nums) {
        // if there exists the same elements ,we may compress them in the assigned array and the solve the problem
        //  we should find the klen arr to sort the nums and simplify the problem
        if (nums.length == 0)
            return 0;
        int maxLen = 10007;
        int[] cnt = new int[maxLen];
        int n = 0;
        for (int num : nums) {
            cnt[num]++;
            n = Math.max(n, num);
        }
        int first = 0;
        int second = 1 * cnt[1];
        int next = 0;
        for (int i = 2; i <= n; i++) {
            next = Math.max(second, first + i * cnt[i]);
            first = second;
            second = next;
        }
        return second;
    }
}
