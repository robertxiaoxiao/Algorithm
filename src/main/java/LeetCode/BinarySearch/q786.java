package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/11/011
 * @description:
 */

public class q786 {

    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int[] ans = new int[2];
        int n = A.length;
        double l = 0;
        double r = 1.0;

        double temp = 0.0;
        int p = -1;
        int q = -1;
        while (l < r) {
            double mid = (l + r) / 2;
            int j = 0;
            int cnt = 0;
            // to record maxVal in current Scan(may not be the last row)
            double max = 0;
            for (int i = 0; i < n; i++) {
                while (j < n && A[i] > A[j] * mid) j++;
                cnt += n - j;
                if (j == n)
                    break;
                temp = (double) A[i] / (double) A[j];
                System.out.println(temp);
                if (temp > max) {
                    max = temp;
                    p = i;
                    q = j;
                }
            }
            if (cnt == K) {
                return new int[]{p, q};
            } else if (cnt > K) {
                r = mid;
            } else
                l = mid;
        }
        return new int[]{};
    }

}
