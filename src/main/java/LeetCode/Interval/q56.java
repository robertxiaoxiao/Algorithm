package LeetCode.Interval;

import java.util.*;

public class q56 {

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        //   TreeSet<int[]> treeSet = new TreeSet<>((a, b) -> a[0] - b[0]);
        List<int[]> list = new LinkedList<>();
        for (int[] k : intervals) {
            if (list.isEmpty() || list.get(list.size() - 1)[1] < k[0])
                list.add(k);
            else {
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], k[1]);
            }
        }


        int n = list.size();
        int[][] ans = new int[n][2];
        int idx = 0;
        for (int[] k : list) {
            ans[idx++] = k;
        }
        return ans;
    }

//        for (int[] k : intervals) {
//            if (treeSet.isEmpty())
//                treeSet.add(k);
//            else {
//                int[] last = treeSet.last();
//                if (last[1] < k[0])
//                    treeSet.add(k);
//                else {
//                    treeSet.remove(last);
//                    treeSet.add(new int[]{last[0], Math.max(last[1], k[1])});
//                }
//            }
//        }
//        int n = treeSet.size();
    //  }


}
