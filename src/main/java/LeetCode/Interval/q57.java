package LeetCode.Interval;

import java.util.LinkedList;
import java.util.List;

public class q57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> ans = new LinkedList<>();
        List<int[]> l = new LinkedList<>();
        List<int[]> r = new LinkedList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        for (int[] interval : intervals) {
            if (interval[1] < start)
                l.add(interval);
            else if (interval[0] > end)
                r.add(interval);
            else {
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }
        }

        for (int[] la : l)
            ans.add(la);
        ans.add(new int[]{start, end});
        for (int[] ra : r)
            ans.add(ra);
        int[][] res = new int[ans.size()][2];
        int idx = 0;
        for (int[] key : ans)
            res[idx++] = key;
        return res;

    }

}
