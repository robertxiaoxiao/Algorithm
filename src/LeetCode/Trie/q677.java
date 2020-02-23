package LeetCode.Trie;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

import java.util.HashMap;

public class q677 {

    HashMap<String, Integer> hm;
    HashMap<String, Integer> words;

    /**
     * Initialize your data structure here.
     */
    public q677() {
        hm = new HashMap<>();
        words = new HashMap<>();
    }

    public void insert(String key, int val) {
        int pre = words.getOrDefault(key, 0);
        int delt = 0;
        if (pre == val)
            return;
        else if (pre != 0)
            delt = -pre;

        for (int i = 0; i < key.length(); i++) {
            String temp = key.substring(0, i + 1);
            int size = hm.getOrDefault(temp, 0) + delt;
            hm.put(temp, val + size);
        }
        words.put(key, val);
    }

    public int sum(String prefix) {
        return hm.getOrDefault(prefix, 0);
    }

}