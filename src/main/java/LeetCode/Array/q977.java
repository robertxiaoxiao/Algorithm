package LeetCode.Array;/*
 * @author: Robert
 * @date:  2020/2/15/015
 * @description:
 */

public class q977 {
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        int i = 0;
        int j = n - 1;
        int idx = n - 1;
        while (idx >= 0) {
            int t1 = A[j] * A[j];
            int t2 = A[i] * A[i];
            if (t1 > t2) {
                ans[idx--] = t1;
                j--;
            }else{
                ans[idx--] = t2;
                i++;
            }
        }
        return ans;
    }

}
