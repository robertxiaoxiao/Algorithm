package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

public class q91 {
    public int mySqrt(int x) {
        if (x == 1)
            return 1;
        long i = 1;
        long j = x / 2;
        while (i <= j) {
            long mid = (i + j) >> 1;
            if (mid * mid == x)
                return (int) mid;
            if (mid * mid <= x)
                i = mid + 1;
            else
                j = mid - 1;
        }
        return (int) i - 1;
    }
}
