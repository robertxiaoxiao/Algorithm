package leetcodeTest.Week16;/*
 * @author: Robert
 * @date:  2020/2/23/023
 * @description:
 */

public class q3 {

    public int[] closestDivisors(int num) {
        int n1 = num + 1;
        int n2 = num + 2;
        int delta = -1;
        int t1 = -1;
        int t2 = -1;
        for (int i = 1; i * i <= n2; i++) {
            if (n1 % i == 0) {
                int tdel = Math.abs(n1 / i - i);
                if (tdel < delta || delta == -1) {
                    t1 = n1 / i;
                    delta = tdel;
                    t2 = i;
                }
            }
            if (n2 % i == 0) {
                int tdel = Math.abs(n2 / i - i);
                if (tdel < delta || delta == -1) {
                    t1 = n2 / i;
                    delta = tdel;
                    t2 = i;
                }
            }
        }
        return new int[]{t1, t2};
    }
//        int[] temp1 = getnums(n1);
//        int[] temp2 = getnums(n2);
//        if (Math.abs(temp1[0] - temp1[1]) < Math.abs(temp2[0] - temp2[1]))
//            return temp1;
//        else
//            return temp2;
    //   }

    public int[] getnums(int n) {
        int a = (int) Math.sqrt(n);
        if (a * a == n)
            return new int[]{a, a};
        int f1 = a;
        int f2 = -1;
        int t1 = -1;
        int t2 = Integer.MAX_VALUE;
        while (f1 < n) {
            if (n % f1 == 0) {
                f2 = n / f1;
                t1 = f1;
                t2 = f2;
                break;
            }
            f1++;
        }
        f1 = a;
        int t3 = -1;
        int t4 = Integer.MAX_VALUE;
        while (f1 > 0) {
            f1--;
            if (n % f1 == 0) {
                f2 = n / f1;
                t3 = f1;
                t4 = f2;
                break;
            }
        }
        if (Math.abs(t3 - t4) < Math.abs(t1 - t2))
            return new int[]{t3, t4};
        else
            return new int[]{t1, t2};
    }
}
