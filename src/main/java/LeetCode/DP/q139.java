package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/31/031
 * @description:
 */

import java.util.HashMap;
import java.util.List;

public class q139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        HashMap<String, Boolean> hm = new HashMap<>();
        return helper(s, wordDict, hm);
    }

    public boolean helper(String s, List<String> wordDict, HashMap<String, Boolean> hm) {

        if (s.length() == 0)
            return true;

        if (hm.containsKey(s))
            return hm.get(s);

        boolean flag = false;
        for (String word : wordDict)
            if (s.startsWith(word))
                flag |= helper(s.substring(word.length()), wordDict, hm);
        hm.put(s, flag);
        return flag;
    }
}
