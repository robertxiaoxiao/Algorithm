package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

public class q875 {


    public int minEatingSpeed(int[] piles, int H) {
        int i = 1;
        int min = -1;
        for (int pile : piles)
            min = Math.max(min, pile);
        int j = min;
        while (i < j) {

            int mid = (i + j) / 2;
            if (caneat(piles, H, mid))
                j = mid;
            else
                i = mid + 1;
        }
        return i;
    }

    public boolean caneat(int[] piles, int H, int eatv) {
        int cnt = 0;
        for (int pile : piles) {
            int d = pile / eatv;
            if (pile % eatv != 0)
                d++;
            cnt += d;
        }
        return cnt <= H;
    }

}
