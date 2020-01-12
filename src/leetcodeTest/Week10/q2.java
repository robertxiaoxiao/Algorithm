package leetcodeTest.Week10;/*
 * @author: Robert
 * @date:  2020/1/12/012
 * @description:
 */

public class q2 {
    public static int minFlips(int a, int b, int c) {
        if ((a | b) == c)
            return 0;
        int times = 0;
        int cnt = 0;
        while (times <= 32) {
            int ca = a % 2;
            int cb = b % 2;
            int cc = c % 2;
            if ((ca | cb) == 1 - cc) {
                if (cc == 0) {
                    cnt += ca + cb;
                } else if (cc == 1)
                    cnt += 1;
            }
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
            times++;
        }
        return cnt;
    }
}
