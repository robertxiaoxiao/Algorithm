package LeetCode.Array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q49 {

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> hm = new HashMap<>();

        for (String s : strs) {
            String t = getHash(s);
            if (!hm.containsKey(t))
                hm.put(t, new LinkedList<>());
            hm.get(t).add(s);
        }

        List<List<String>> ans = new LinkedList<>();
        for (String key : hm.keySet())
            ans.add(hm.get(key));
        return ans;
    }

    public String getHash(String s) {

        int[] nums = new int[26];
        for (char c : s.toCharArray()) {
            nums[c - 'a']++;
        }
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 26; i++) {
            if (nums[i] != 0) {
                char c = (char) (i + 'a');
                sb.append(c).append(nums[i]);
            }
        }
        return sb.toString();
    }

}
