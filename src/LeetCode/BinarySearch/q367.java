package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

public class q367 {

    public boolean isPerfectSquare(int num) {
        if (num == 1)
            return true;
        int s = sqr(num);
        return s * s == num;
    }

    public int sqr(int sum) {
        long i = 1;
        long j = sum;
        while (i < j) {
            long mid = i + (j - i) / 2;
            if (mid * mid > sum)
                j = mid;
            else
                i = mid + 1;
        }
        return (int) i - 1;
    }
}
