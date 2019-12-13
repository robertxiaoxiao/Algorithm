package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/12/012
 * @description:
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class q47 {

    /*
    Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();

        LinkedList<Integer> temp = new LinkedList<>();
        Arrays.sort(nums);
        boolean[] took = new boolean[nums.length];
        dfs(0, nums, took, temp, res);
        return res;
    }

    public void dfs(int cnt, int[] nums, boolean[] took, List<Integer> temp, List<List<Integer>> res) {
        if (cnt == nums.length) {
            res.add(new LinkedList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (took[i])
                continue;
            /*
               a    b   c
               a    c   b
               b    a   c   X
               b    c   a
               c    a   b   X
               c    b   a   X
             */
            if (i > 0 && nums[i] == nums[i - 1] && !took[i - 1])
                continue;
            if (took[i] == false) {
                temp.add(nums[i]);
                took[i] = true;
                dfs(cnt + 1, nums, took, temp, res);
                temp.remove(temp.size() - 1);
                took[i] = false;
            }
        }
    }

    public static String IntToStr(List<Integer> temp) {
        StringBuilder sb = new StringBuilder();
        for (int num : temp)
            sb.append(num);
        return sb.toString();
    }
}
