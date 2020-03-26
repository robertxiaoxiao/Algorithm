package leetcodeTest.Week11;/*
 * @author: Robert
 * @date:  2020/1/19/019
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q1 {

    public int maximum69Number(int num) {
        int cnt = 0;
        int temp = num;
        List<Integer> res = new LinkedList<>();
        int bit = 0;
        while (temp != 0) {
            int k = temp % 10;
            res.add(0, k);
            temp = (temp - k) / 10;
            bit++;
        }
        boolean flag = false;
        int ans = 0;
        for (int i : res) {
            if (i == 9 || flag)
                ans = ans * 10 + i;

            if (i == 6 && !flag) {
                ans = ans * 10 + 9;
                flag = true;
            }
        }
        return ans;
    }
}
