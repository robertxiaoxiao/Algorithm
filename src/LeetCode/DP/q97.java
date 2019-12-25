package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/25/025
 * @description:
 */

import java.util.HashMap;

public class q97 {


    public boolean isInterleave(String s1, String s2, String s3) {

        HashMap<String, Boolean> mem = new HashMap<String, Boolean>();
        return helper(s1, s2, s3, mem);
    }

    public boolean helper(String s1, String s2, String s3, HashMap<String, Boolean> mem) {

        String temp = s1 + "_" + s2 + "_" + s3;
        if (mem.containsKey(temp))
            return mem.get(temp);

        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0)
            return true;

        boolean flag = false;
        if (s1.length() > 0 && s3.length() > 0 && s1.charAt(0) == s3.charAt(0))
            flag |= helper(s1.substring(1), s2, s3.substring(1), mem);

        if (s2.length() > 0 && s3.length() > 0 && s2.charAt(0) == s3.charAt(0))
            flag |= helper(s1, s2.substring(1), s3.substring(1), mem);
        mem.put(temp, flag);
        return flag;
    }

}
