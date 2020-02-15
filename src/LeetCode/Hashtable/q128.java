package LeetCode.Hashtable;/*
 * @author: Robert
 * @date:  2020/2/15/015
 * @description:
 */

import java.util.HashMap;
import java.util.HashSet;

public class q128 {

    //  when time complicity at O(nlogn) reduced to O(n) , using hashtable or hashmap as extra space
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (hm.containsKey(nums[i]))
                continue;
            int key = nums[i];
            int l = hm.getOrDefault(key - 1, 0);
            int r = hm.getOrDefault(key + 1, 0);
            int t = l + r + 1;
            ans = Math.max(ans, t);
            hm.put(key, t);
            hm.put(key - l, t);
            hm.put(key + r, t);
        }

        return ans;
    }

    public int longestConsecutiveHashset(int[] nums) {

        HashSet<Integer> hset = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            hset.add(nums[i]);
        }
        int ans = 0;
        for (int i : nums) {
            if (hset.contains(i - 1))
                continue;
            int k = i;
            while (hset.contains(k)) {
                k++;
            }
            ans = Math.max(ans, k - i);
        }
        return ans;
    }

    public int longestConsecutiveNaive(int[] nums) {
        int ans = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            hm.put(cur, hm.getOrDefault(cur - 1, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (visited.contains(nums[i]))
                continue;
            int cur = nums[i];
            int pre = cur - hm.get(cur);
            int t = hm.get(cur);
            while (hm.containsKey(pre)) {
                t += hm.get(pre);
                visited.add(pre);
                pre = pre - hm.get(pre);
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
