package LeetCode.Greedy;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

import java.util.HashMap;

public class q409 {

    public int longestPalindrome(String s) {
        int n = s.length();
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : s.toCharArray())
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        int sum = 0;
        for (char c : hm.keySet()) {
            int cnt = hm.get(c);
            if (cnt % 2 == 0)
                sum += cnt;
            else {
                sum += cnt / 2 * 2;
                if (sum % 2 == 0)
                    sum++;
            }
        }
        return sum;
    }
}
