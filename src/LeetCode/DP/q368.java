package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class q368 {
    public static void main(String[] args) {

    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer>[] dp = new List[n];
        dp[0] = new LinkedList<>();
        dp[0].add(nums[0]);
        for (int i = 1; i < n; i++) {
            List<Integer> temp = null;
            int Maxsize = 1;
            int curnum = nums[i];
            for (int j = 0; j < i; j++) {
                if (curnum % nums[j] == 0 || nums[j] % curnum == 0) {
                    if (check(dp[j], curnum) && dp[j].size() >= Maxsize) {
                        temp = dp[j];
                        Maxsize = dp[j].size();
                    }
                }
            }
            dp[i] = new LinkedList<>();
            if (temp != null)
                dp[i].addAll(temp);
            dp[i].add(nums[i]);
        }
        int size = 1;
        List<Integer> temp = dp[0];
        for (List<Integer> res : dp)
            if (res.size() >= size) {
                size = res.size();
                temp = res;
            }
        return temp;
    }

    // mem the path
    public List<Integer> largestDivisibleSubsetUsingdp(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] path = new int[n];
        Arrays.fill(path, -1);
        dp[0] = 1;
        int maxlen = 0;
        int maxidx = 0;
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--)
                if (cur % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    path[i] = j;
                }
            if (dp[i] > maxlen) {
                maxlen = dp[i];
                maxidx = i;
            }
        }
        List<Integer> res = new LinkedList<>();
        while (maxidx != -1) {
            res.add(nums[maxidx]);
            maxidx = path[maxidx];
        }
        return res;
    }

    public boolean check(List<Integer> res, int a) {

        for (int i : res) {
            if (i % a == 0 || a % i == 0)
                continue;
            else
                return false;
        }
        return true;
    }

}
