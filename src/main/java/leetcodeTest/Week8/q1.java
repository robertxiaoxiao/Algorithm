package leetcodeTest.Week8;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.List;

public class q1 {
    public int[] sumZero(int n) {
        int half = n / 2;
        int[] res = new int[n];
        int pre = -half;
        int k=0;
        for (int i = 0; i < half; ) {
            res[k] = pre;
            res[k + 1] = -pre;
            i = i + 1;
            k++;
            pre++;
        }
        if (n % 2 == 1)
            res[n - 1] = 0;
        return res;
    }
}
