package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

public class q278 {


    public int firstBadVersion(int n) {
        int i = 1;
        int j = n+1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (isBadVersion(mid))
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }

    public boolean isBadVersion(int version) {
        return true;
    }

}
