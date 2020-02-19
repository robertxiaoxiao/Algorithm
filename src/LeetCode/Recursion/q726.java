package LeetCode.Recursion;/*
 * @author: Robert
 * @date:  2020/2/15/015
 * @description:
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class q726 {

    public static void main(String[] args) {
        String s = "Mg(OH)2";
        q726 q = new q726();
        System.out.println(q.countOfAtoms(s));
    }

    public String countOfAtoms(String formula) {
        idx = 0;
        Map<String, Integer> res = parse(formula);
        StringBuffer sb = new StringBuffer();
        for (String key : res.keySet()) {
            int cnt = res.get(key);
            if (cnt > 1)
                sb.append(key).append(cnt);
            else
                sb.append(key);
        }
        return sb.toString();
    }

    public Map<String, Integer> parse(String formula) {
        Map<String, Integer> counts = new TreeMap<>();
        // the output of iteration
        while (idx != formula.length()) {
            if (formula.charAt(idx) == '(') {
                idx++;
                Map<String, Integer> temp = parse(formula);
                int cnt = getnum(formula);
                for (String key : temp.keySet())
                    counts.put(key, counts.getOrDefault(key, 0) + temp.get(key) * cnt);
            } else if (formula.charAt(idx) == ')') {
                idx++;
                return counts;
            } else {
                String t = getToken(formula);
                int cnt = getnum(formula);
                counts.put(t, counts.getOrDefault(t, 0) + cnt);
            }
        }
        return counts;
    }

    static int idx = 0;

    public int getnum(String formula) {
        int i = idx;
        while (idx < formula.length() && Character.isDigit(formula.charAt(idx)))
            idx++;

        if (idx == i)
            return 1;
        return Integer.parseInt(formula.substring(i, idx));
    }

    public String getToken(String formula) {
        int i = idx;
        if (Character.isUpperCase(formula.charAt(idx))) {
            idx++;
            while (idx < formula.length() && Character.isLowerCase(formula.charAt(idx)))
                idx++;
        }
        return formula.substring(i, idx);
    }
}
