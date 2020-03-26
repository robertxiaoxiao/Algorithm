package leetcodeTest.Week7;/*
 * @author: Robert
 * @date:  2019/12/22/022
 * @description:
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class q2 {

    // it must use  treeMap ,if the data size is too large ;
    public boolean isPossibleDivideTest(int[] nums, int k) {
        if (nums.length == 0)
            return false;

        if (nums.length % k != 0)
            return false;

        TreeMap<Integer, Integer> hm = new TreeMap<Integer, Integer>();

        for (int num : nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }

        while (!hm.isEmpty()) {
            int cur = hm.lastKey();
            int times = hm.get(cur);
            if (times < 0)
                return false;
            if (times == 0)
                hm.remove(cur);
            if (times > 0) {
                int first = cur - k + 1;
                while (first <= cur) {
                    if (!hm.containsKey(first))
                        return false;
                    hm.put(first, hm.get(first) - times);
                    ++first;
                }
                hm.remove(cur);
            }
        }
        return true;
    }
}
