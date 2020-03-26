package LeetCode.Array;/*
 * @author: robert
 * @date:  2019/10/12/012
 * @description:
 */


import java.util.Queue;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class q4 {
    public static void main(String[] args) {
        Queue<Integer> queue =new LinkedBlockingQueue<>();
        int[] a = {1, 3, 4};
        int[] b = {2};
        System.out.println(findMedianSortedArrays(b, a));
    }

    //  EDGE PROCESS IS EASIER WHEN CONSIDERING IT CLEARLY
    public static double findMedianSortedArrays(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        // arrayA[m-1] <arrayB[0] ;

        int imin = 0;
        int imax = m;
        //put the median in the left
        int length = (m + n + 1) / 2;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = length - i;
            // notice the judge condition
            if (i < imax && b[j - 1] > a[i])
                imin = i + 1;
            else if (i > imin && a[i - 1] > b[j])
                imax = i - 1;

            else {
                int maxleft = 0;

                if (i == 0)
                    maxleft = b[j - 1];
                else if (j == 0)
                    maxleft = a[i - 1];
                else {
                    maxleft = Math.max(b[j - 1], a[i - 1]);
                }

                if ((m + n) % 2 == 1)
                    return maxleft;

                int minright = 0;
                if (i == m) {
                    minright = b[j];
                } else if (j == n) {
                    minright = a[i];
                } else {
                    minright = Math.min(b[j], a[i]);
                }

                if ((m + n) % 2 == 0)
                    return (double) (maxleft + minright) / 2;

            }

        }

        return 0;
    }

    // the simply expreesion is the correct  ;
    public double findMedianSortedArraysErroe(int[] a, int[] b) {
        int m = a.length;
        int n = b.length;
        // arrayA[m-1] <arrayB[0] ;

        int start = 0;
        int end = m - 1;
        //put the median in the left
        int length = (m + n + 1) / 2;

        int i = 0;
        int j = length - i;

        int leftmax = 0;
        int rightmin = 0;

        while (start < end) {
            i = (start + end) / 2;

            j = length - i - 2;
            if (j == -1) {
                leftmax = a[i];
                rightmin = Math.min(a[i + 1], b[0]);
                break;
            }


            if (i + 1 < m && j + 1 < n && a[i] <= b[j + 1] && b[j] <= a[i + 1]) {
                System.out.println("current (i,j): " + i + "   " + j);
                leftmax = Math.max(a[i], b[j]);
                rightmin = Math.min(b[j + 1], a[i + 1]);
                break;
            }

            // the end of smaller array
            else if (j == n - 1) {
                leftmax = Math.min(a[i + 1], b[j]);
                rightmin = Math.max(a[i + 1], b[j]);
                break;
            } else if (i < m && j + 1 < n && a[i] > b[j + 1])
                end = i;
            else if (i + 1 < m && j < n && b[j] > a[i + 1])
                start = i;

            System.out.println("current (i,j): " + i + "    " + j);
        }

        if ((m + n) % 2 == 0)
            return (double) (leftmax + rightmin) / 2;
        else
            return (double) leftmax;

    }
}
