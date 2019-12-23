package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/20/020
 * @description:
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q140 {
    public List<String> wordBreak(String s, List<String> wordDict) {

        HashMap<String, List<String>> hm = new HashMap<>();
        return memDp(s, wordDict, hm);
        // return helper(s, wordDict);
    }

    public List<String> memDp(String s, List<String> wordDict, HashMap<String, List<String>> hm) {
        List<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add(new String(""));
            hm.put("", res);
            return res;
        }

        if (hm.containsKey(s))
            return hm.get(s);

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> nextstr = memDp(s.substring(word.length()), wordDict, hm);
                if (nextstr.size() != 0) {
                    for (String nstr : nextstr) {
                        StringBuffer sb = new StringBuffer();
                        if (nstr.equalsIgnoreCase(""))
                            sb.append(word);
                        else
                            sb.append(word + " " + nstr);
                        res.add(sb.toString());
                    }
                }
            }
        }
        hm.put(s, res);
        return res;
    }

    // naive recursion
    public List<String> helper(String s, List<String> wordDict) {

        List<String> res = new LinkedList<>();
        if (s.length() == 0) {
            res.add(new String(""));
            return res;
        }
        for (String word : wordDict) {
            if (s.equalsIgnoreCase(word))
                res.add(word);
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> nextstr = helper(s.substring(word.length()), wordDict);
                if (nextstr.size() != 0) {
                    for (String nstr : nextstr) {
                        StringBuffer sb = new StringBuffer();
                        if (nstr.equalsIgnoreCase(""))
                            sb.append(word);
                        else
                            sb.append(word + " " + nstr);
                        res.add(sb.toString());
                    }
                }
            }
        }

        return res;

    }

}
