package LeetCode.TwoPointers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class q18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> ans = new LinkedList<>();

        HashSet<String> hashSet = new HashSet<>();

        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i + 3 < n; i++) {
            for (int j = i + 1; j + 2 < n; j++) {
                int rest = target - nums[i] - nums[j];
                int k = j + 1;
                int m = n - 1;
                while (k < m) {
                    int cur = nums[k] + nums[m];
                    if (cur == rest) {
                        List<Integer> temp = new LinkedList<>();
                        StringBuffer sb = new StringBuffer();
                        int ni = nums[i];
                        int nj = nums[j];
                        int nk = nums[k];
                        int nm = nums[m];
                        sb.append(ni + '-');
                        sb.append(nj + '-');
                        sb.append(nk + '-');
                        sb.append(nm + '-');
                        String ts = sb.toString();
                        if (!hashSet.contains(ts)) {
                            hashSet.add(ts);
                            temp.add(ni);
                            temp.add(nj);
                            temp.add(nk);
                            temp.add(nm);
                            ans.add(temp);
                        }
                    }
                    if (cur >= rest)
                        m--;
                    if (cur <= rest)
                        k++;
                }
            }
        }
        return ans;
    }
}
