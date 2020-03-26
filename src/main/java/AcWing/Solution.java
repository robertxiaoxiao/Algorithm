package AcWing;

import java.util.LinkedList;
import java.util.List;

public class Solution {

    public int remove(int[] nums) {
        List<Integer> list = new LinkedList<>();
        for (int i : nums)
            list.add(i);
        int maxlen = 1;
        for (int i = 0; i < list.size(); i++) {
            if (i + 1 < list.size() && list.get(i + 1) < list.get(i)) {
                int todelet = list.get(i);
                list.remove(i);
                maxlen = Math.max(maxlen, getMaxlen(list));
                list.add(i, todelet);
            }
        }
        return maxlen;
    }

    public int getMaxlen(List<Integer> list) {
        int maxlen = -1;
        for (int i = 0; i < list.size(); ) {
            int pre = i;
            while (i + 1 < list.size() && list.get(i) <= list.get(i + 1))
                i++;
            maxlen = Math.max(maxlen, i - pre + 1);
            i++;
        }
        return maxlen;
    }
    

}
