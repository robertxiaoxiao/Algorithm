package LeetCode.Simulation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

class MyCalendarII {

    TreeMap<Integer, Integer> record;
    List<int[]> overlaps;
    List<int[]> booked;

    public MyCalendarII() {
        overlaps = new LinkedList<>();
        booked = new LinkedList<>();
    }

    public boolean book(int start, int end) {

        //   l r
        //   s e
        for (int[] l : overlaps) {
            if (Math.max(l[0], start) < Math.min(l[1], end))
                return false;
        }

        int[] cur = new int[]{start, end};
        booked.add(cur);
        for (int[] k : booked) {
            int ss = Math.max(k[0], start);
            int se = Math.min(k[1], end);
            if (ss < se)
                overlaps.add(new int[]{ss, se});
        }
        return true;
    }
//    public MyCalendarII() {
//        record = new TreeMap<>();
//
//    }
//
//
//    public boolean book(int start, int end) {
//        int pres = record.getOrDefault(start, 0);
//        int pree = record.getOrDefault(end, 0);
//        record.put(start, pres + 1);
//        record.put(end, pree - 1);
//        int sum = 0;
//        for (int key : record.keySet()) {
//            sum += record.get(key);
//            if (key > end)
//                break;
//            if (sum >= 3) {
//                record.put(start, pres);
//                record.put(end, pree);
//                return false;
//            }
//        }
//        return true;
//    }

}
