package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/17/017
 * @description:
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.CheckedInputStream;

public class q93 {
    public List<String> restoreIpAddresses(String s) {

        HashSet<String> hset = new HashSet<>();
        int n = s.length();
        cut(0, 3, "", s, hset);
        return new LinkedList<>(hset);
    }

    // notice the edge situation   it may not start with 0x or 255+ ;
    // proceed the first two cut ,we check it in a way ,and DON'T forget to check it in the final remaining section
    public void cut(int i, int cutnum, String temp, String s, HashSet<String> hset) {
        if (i >= s.length())
            return;
        if (cutnum == 0) {
            if (s.length() - i > 3)
                return;
            String adder = s.substring(i);
            if (Integer.parseInt(adder) > 255)
                return;
            if (adder.length() != 1 && adder.startsWith("0"))
                return;
            String cur = temp + adder;
            hset.add(cur);
            return;
        }

        for (int j = i + 1; j < s.length() && j < i + 4; j++) {
            StringBuffer sb = new StringBuffer();

            if (j != i + 1 && s.charAt(i) == '0')
                continue;
            if (Integer.parseInt(s.substring(i, j)) > 255)
                continue;
            sb.append(temp + s.substring(i, j) + ".");
            cut(j, cutnum - 1, sb.toString(), s, hset);
        }
    }
}
