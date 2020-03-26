package leetcodeTest.Week16;/*
 * @author: Robert
 * @date:  2020/2/23/023
 * @description:
 */

public class q1 {

    public int daysBetweenDates(String date1, String date2) {
        int[] m = new int[13];
        m[1] = 31;
        m[2] = 28;
        m[3] = 31;
        m[4] = 30;
        m[5] = 31;
        m[6] = 30;
        m[7] = 31;
        m[8] = 31;
        m[9] = 30;
        m[10] = 31;
        m[11] = 30;
        m[12] = 31;

        String[] de1 = date1.split("-");
        String[] de2 = date2.split("-");
        int y1 = Integer.parseInt(de1[0]);
        int y2 = Integer.parseInt(de2[0]);
        int m1 = Integer.parseInt(de1[0]);
        int m2 = Integer.parseInt(de2[0]);
        int d1 = Integer.parseInt(de1[0]);
        int d2 = Integer.parseInt(de2[0]);
        int l = getDays(y1, m1, d1, m);
        int f = getDays(y2, m2, d2, m);
        if (y1 < y2) {
            return  Math.abs(getyearsdaye(y1, y2) + f - l);
        } else
            return  getyearsdaye(y2, y1) + l - f;

    }

    public int getDays(int y, int mon, int d, int[] m) {
        int cnt = 0;
        for (int i = 1; i < mon; i++)
            cnt += m[i];
        if (check(y) && mon > 2) {
            cnt += 1;
        }
        return cnt + d;
    }

    public int getyearsdaye(int y1, int y2) {
        int i = y1;
        int sum = 0;
        while (i < y2) {
            if (check(i))
                sum += 366;
            else
                sum += 365;
            i++;
        }
        return sum;
    }

    public boolean check(int y) {
        return (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0));
    }
}
