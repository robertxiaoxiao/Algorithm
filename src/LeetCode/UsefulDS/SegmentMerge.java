package LeetCode.UsefulDS;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SegmentMerge {


    class Segment {
        int l;
        int r;

        public Segment(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public int getLen(List<Segment> segments) {

        Collections.sort(segments, new Comparator<Segment>() {
            @Override
            public int compare(Segment o1, Segment o2) {
                return o1.l - o2.l;
            }
        });
        int res = 0;
        int l = Integer.MIN_VALUE;
        int r = Integer.MIN_VALUE;
        for (Segment seg : segments) {
            int cl = seg.l;
            int cr = seg.r;
            if (cl > r) {
                res += r - l;
                l = cl;
                r = cr;
            } else {
                r = Math.max(cr, r);
            }
        }

        return res;
    }

}
