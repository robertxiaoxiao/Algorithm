package LeetCode.Interval;

import java.util.Comparator;
import java.util.TreeSet;

public class Test {

    static class Interval {
        int l, r;

        public Interval(int l, int r) {
            this.l = l;
            this.r = r;
        }

        // if using a-b ,it will cause overflow
//        @Override
//        public int compareTo(Interval o) {
//            return Integer.compare(o.l, this.l);
//        }

    }

    public static TreeSet<Interval> set;

    public static void insert(int x) {
        Interval interval = new Interval(x, x);
        Interval l = set.floor(interval);
        Interval r = set.ceiling(interval);
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
        //   System.out.printf("%d %d %d ", l.l, l.r, x);

        //  System.out.println(left);
        if (right && left) {
            set.remove(l);
            set.remove(r);
            set.add(new Interval(l.l, r.r));
        } else if (right) {
            set.remove(r);
            set.add(new Interval(x, r.r));
            //     System.out.printf("add [%d %d]\r\n", x, r.r);
        } else if (left) {
            System.out.println(set.add(new Interval(l.l, x)));
            System.out.println(set.remove(l));
            //      System.out.println(set.size());
        } else
            set.add(interval);

        // System.out.println(set.size());
    }

    static int INF = (int) 1e6;

    public static void main(String[] args) {
        set = new TreeSet<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                // if compare is the same ,insert  operation will be not  complete
                if (o1.l == o2.l)
                    return o1.r - o2.r;
                return o1.l - o2.l;
            }
        });

        set.add(new Interval(INF, INF));
        set.add(new Interval(-INF, -INF));
        for (int k = 2; k <= 5; k++) {
            insert(k);
            System.out.println(set.size());
        }


    }
}
