package LeetCode.Interval;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.util.*;

public class Acq832 {
    /*
           maintain a interval set in each row and col ;
     */

    static int n;
    static int r;
    static int c;
    static int xr;
    static int xc;
    static String orders;
    static TreeSet<Interval>[] rows;
    static TreeSet<Interval>[] cols;
    static int INF = (int) 1e9;

    static class Interval implements Comparable<Interval> {
        int l, r;

        public Interval(int l, int r) {
            this.l = l;
            this.r = r;
        }

        // if using a-b ,it will cause overflow
        @Override
        public int compareTo(Interval o) {
            return Integer.compare(this.l, o.l);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();

        StringBuffer sb = new StringBuffer();
        for (int cases = 1; cases <= N; cases++) {
            n = sc.nextInt();
            r = sc.nextInt();
            c = sc.nextInt();
            xr = sc.nextInt();
            xc = sc.nextInt();
            sc.nextLine();
            orders = sc.nextLine();
            rows = new TreeSet[r + 1];
            for (int i = 1; i <= r; i++) {
                Interval max = new Interval(INF, INF);
                Interval min = new Interval(-INF, -INF);
                rows[i] = new TreeSet<>();
                rows[i].add(max);
                rows[i].add(min);
            }
            cols = new TreeSet[c + 1];

            for (int i = 1; i <= c; i++) {
                Interval max = new Interval(INF, INF);
                Interval min = new Interval(-INF, -INF);
                cols[i] = new TreeSet<>();
                cols[i].add(max);
                cols[i].add(min);
            }

            for (int i = 0; i < n; i++) {
                int cr = xr;
                int cc = xc;
                char ch = orders.charAt(i);
                if (ch == 'N') {
                    cr = move(cols[xc], xr, -1);
                } else if (ch == 'S') {
                    cr = move(cols[xc], xr, 1);
                } else if (ch == 'W') {
                    cc = move(rows[xr], xc, -1);
                } else if (ch == 'E') {
                    cc = move(rows[xr], xc, 1);
                }
                insert(rows[xr], xc);
                insert(cols[xc], xr);
                xr = cr;
                xc = cc;
            }
            // System.out.printf("Case #%d: %d %d\n", cases, xr, xc);
            sb.append(String.format("Case #%d: %d %d\n", cases, xr, xc));
        }
        System.out.println(sb);
    }

    public static void insert(TreeSet<Interval> set, int x) {
        Interval interval = new Interval(x, x);
        Interval l = set.lower(interval);
        Interval r = set.higher(interval);
//        if (l == null && r == null) {
//            set.add(interval);
//        } else if (l == null) {
//            if (interval.r + 1 == r.l) {
//                set.remove(r);
//                set.add(new Interval(interval.l, r.r));
//            } else
//                set.add(interval);
//        } else if (r == null) {
//            if (l.r + 1 == interval.l) {
//                set.remove(l);
//                set.add(new Interval(l.l, interval.r));
//            }
//        } else {
        boolean right = x + 1 == r.l;
        boolean left = l.r + 1 == x;

        if (right && left) {
            set.remove(l);
            set.remove(r);
            set.add(new Interval(l.l, r.r));

        } else if (right) {
            set.remove(r);
            set.add(new Interval(x, r.r));
            //         System.out.printf("add [%d %d]\r\n",x,r.r);
        } else if (left) {
            // remove must  be operated first
            set.remove(l);
            set.add(new Interval(l.l, x));
        } else
            set.add(interval);

        // System.out.println(set.size());
    }

    public static int move(TreeSet<Interval> set, int cc, int dir) {
        Interval cur = new Interval(cc, cc);
        if (dir == 1) {
            Interval r = set.higher(cur);
            if (r.l == cc + 1)
                return r.r + 1;
            else
                return cc + 1;
        } else {
            Interval l = set.lower(cur);
            if (l.r + 1 == cur.l)
                return l.l - 1;
            else
                return cc - 1;
        }
    }

}
