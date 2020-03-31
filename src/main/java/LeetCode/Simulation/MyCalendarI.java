package LeetCode.Simulation;

import java.util.*;

class MyCalendar {


    List<int[]> booked;

    public MyCalendar() {
        booked = new LinkedList<>();
    }

    public boolean book(int start, int end) {
        for (int[] l : booked)
            if (Math.max(start, l[0]) < Math.min(l[1], end))
                return false;
        booked.add(new int[]{start, end});
        return true;
    }

//    TreeMap<Integer, Integer> record;
//
//    public MyCalendar() {
//        record = new TreeMap<>();
//    }
//
//    //  floor <= key  ceil>=key
//    //  floor.end>start
//    // ceil.start<end
//    public boolean book(int start, int end) {
//
//        Map.Entry<Integer, Integer> s1 = record.floorEntry(end - 1);
//        if (s1 != null && s1.getValue() > start) {
//            return false;
//        }
//
//        Map.Entry<Integer, Integer> s2 = record.ceilingEntry(start);
//        if (s2 != null && s2.getKey() < end)
//            return false;
//
//        record.put(start, end);
//        return true;
//    }
//

}
