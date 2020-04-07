package LeetCode.TwoPointers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class q349 {

    public int[] intersection(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1 = nums1.length;
        int n2 = nums2.length;
        List<Integer> ans = new LinkedList<>();
        for (int i = 0, j = 0; i < n1 && j < n2; ) {
            if (nums1[i] == nums2[j]) {
                ans.add(nums1[i]);
                int pre = i;
                while (i < n1 && nums1[pre] == nums1[i])
                    i++;
                pre = j;
                while (j < n2 && nums2[pre] == nums2[j])
                    j++;

            } else if (nums1[i] < nums2[j])
                i++;
            else
                j++;
        }
        int[] res = new int[ans.size()];
        int idx = 0;
        for (int i : ans)
            res[idx++] = i;
        return res;
    }
}
