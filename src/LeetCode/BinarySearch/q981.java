package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

import java.lang.annotation.Target;
import java.util.*;

public class q981 {

    class TimeMap {

        class node {
            String value;
            int timestamp;

            public node(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }

        HashMap<String, List<node>> map;
        HashMap<String, Boolean> sorted;

        public TimeMap() {
            map = new HashMap<>();
            sorted = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
                sorted.put(key, false);
            }
            map.get(key).add(new node(value, timestamp));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key))
                return "";
//            if (!sorted.get(key)) {
//                Collections.sort(map.get(key), new Comparator<node>() {
//                    @Override
//                    public int compare(node o1, node o2) {
//                        return o1.timestamp - o2.timestamp;
//                    }
//                });
//                sorted.put(key, true);
//            }
            int idx = findlessthantarget(map.get(key), timestamp);
            if (idx == -1)
                return "";
            return map.get(key).get(idx).value;
        }

        public int findlessthantarget(List<node> res, int target) {
            int i = 0;
            int j = res.size() - 1;
            while (i <= j) {
                int mid = i + (j - i) / 2;
                if (res.get(mid).timestamp > target)
                    j = mid - 1;
                else
                    i = mid + 1;
            }
            return i - 1;
        }

//        public String get(String key, int timestamp) {
//            if (!map.containsKey(key))
//                return "";
//            node res = binaryserach(map.get(key), timestamp);
//            return res == null ? "" : res.value;
//        }
//
//        public node binaryserach(List<node> res, int target) {
//            if (res.isEmpty())
//                return null;
//
//            int i = 0;
//            int j = res.size();
//            node cur = null;
//            while (i <= j) {
//                int mid = (i + j) >> 1;
//                int ct = res.get(mid).timestamp;
//                if (ct == target)
//                    return res.get(mid);
//                else if (ct > target)
//                    j = mid - 1;
//                else {
//                    i = mid + 1;
//                    cur = res.get(mid);
//                }
//            }
//            return cur;
//        }


    }
}
