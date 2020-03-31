package LeetCode.Simulation;

import java.util.TreeMap;

public class MyCalendarIII {

    class MyCalendarThree {
        TreeMap<Integer, Integer> record;

        public MyCalendarThree() {
            record = new TreeMap<>();
        }

        public int book(int start, int end) {

            int ps = record.getOrDefault(start, 0);
            int pe = record.getOrDefault(end, 0);
            record.put(start, ps + 1);
            record.put(end, pe - 1);
            int max = -1;
            int sum = 0;
            for (int k : record.keySet()) {
                sum += record.get(k);
                if (sum >= max)
                    max = sum;
            }
            return max;
        }
    }




}
