package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

public class q668 {

    public int findKthNumber(int m, int n, int k) {
        int start = 1;
        int end = m * n;
        // [l,r)
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (calcalutae(m, n, mid) < k)
                start = mid + 1;
            else
                end = mid;
        }
        /*  [l,r]
         while (start <=end) {
            int mid = start + (end - start) / 2;
            if (calcalutae(m, n, mid) < k)
                start = mid + 1;
            else
                end = mid-1;
        }
         */
        return start;
    }

    private int calcalutae(int m, int n, int mid) {
        int i = 1;
        int j = n;
        int cnt = 0;
        while (i <= m && j >= 1) {
            if (mid < i * j) {
                j--;
            } else {
                i++;
                cnt += j;
            }
        }
        return cnt;
    }

    // 1 ...m
    // .
    // n
}
