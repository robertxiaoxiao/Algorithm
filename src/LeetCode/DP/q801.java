package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/20/020
 * @description:
 */

public class q801 {


    public static void swap(int[] A, int[] B, int i) {
        int temp = A[i];
        A[i] = B[i];
        B[i] = temp;
    }

    public int minSwap(int[] a, int[] b) {

        // dp[i] : the min steps to swap to meet the constrains

        return helper(a, b, 0);
    }

    // current state : i-1 state i state
    public int minSwapUsingDP(int[] a, int[] b) {

        int n = a.length;
        // min cost of  not swapping i to make increasing sequence
        int[] n1 = new int[n];

        // min cost of  swapping i to make increasing sequence
        int[] s2 = new int[n];
        n1[0] = 0;
        s2[0] = 1;

        // consider each situation which may occur in the iteration and make it clear at each step
        for (int i = 1; i < n; i++) {
            if (a[i - 1] >= b[i] || b[i - 1] >= a[i]) {
                n1[i] = n1[i - 1];
                // swap at the same time
                s2[i] = s2[i - 1] + 1;
                // it must swap once ,so it must get the different state  with state before;
            } else if (a[i - 1] >= a[i] || b[i - 1] >= b[i]) {
                n1[i] = s2[i - 1];
                s2[i] = n1[i - 1] + 1;
            } else {
                n1[i] = Math.min(n1[i - 1], s2[i - 1]);
                s2[i] = Math.min(n1[i - 1], s2[i - 1]) + 1;
            }
        }

        return Math.min(n1[n - 1], s2[n - 1]);
    }

    public int minStepsCompress(int[] a, int[] b) {
        int n = a.length;
        // min cost of  not swapping i to make increasing sequence
        int swaps = 1;
        int keeps = 0;
        // min cost of  swapping i to make increasing sequence

        // consider each situation which may occur in the iteration and make it clear at each step
        for (int i = 1; i < n; i++) {
            if (a[i - 1] >= b[i] || b[i - 1] >= a[i]) {

                // swap at the same time
                //  a  i-1  i  it must be ordered below
                //  b  i-1  i
                swaps++;
                // it must swap once ,so it must get the different state  with state before;
            } else if (a[i - 1] >= a[i] || b[i - 1] >= b[i]) {
                keeps = swaps;
                swaps = +1;
            } else {
                int min = Math.min(swaps, keeps);
                keeps = min;
                swaps = min + 1;
            }
        }

        return Math.min(keeps, swaps);
    }

    public int helper(int[] a, int[] b, int begin) {

        if (begin == a.length)
            return 0;
        int notswaped = Integer.MAX_VALUE;
        int swaped = Integer.MAX_VALUE;
        int mustswap = Integer.MAX_VALUE;
        if (begin == 0 || (a[begin] > a[begin - 1] && b[begin] > b[begin - 1])) {
            notswaped = helper(a, b, begin + 1);
            swap(a, b, begin);
            swaped = helper(a, b, begin + 1) + 1;
        } else {
            swap(a, b, begin);
            mustswap = helper(a, b, begin + 1) + 1;
        }
        return Math.min(mustswap, Math.min(notswaped, swaped));
    }
}
