package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/30/030
 * @description:
 */

import java.util.Arrays;

public class q322 {

    public int coinChange(int[] coins, int amount) {
        int[] mem = new int[amount + 1];
        int ans = helper(coins, amount, mem);
        return ans == Integer.MAX_VALUE / 2 ? -1 : ans;
    }

    public int helper(int[] coins, int amount, int[] mem) {
        if (amount == 0)
            return 0;
        if (mem[amount] != 0)
            return   mem[amount];
        int min = Integer.MAX_VALUE / 2;
        for (int coin : coins) {
            if (amount >= coin)
                min = Math.min(min, helper(coins, amount - coin, mem) + 1);
        }
        return mem[amount] = min;
    }
}
