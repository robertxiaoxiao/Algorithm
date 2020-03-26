package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/18/018
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q842 {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new LinkedList<>();
        int n = S.length();
        for (int i = 1; i <= n / 2; i++) {
            String s1 = S.substring(0, i);
            if (s1.length() >= 10 || (s1.length() != 1 && s1.startsWith("0")))
                continue;
            for (int j = i + 1; j <= i + n - Math.max(i, j); j++) {

                String s2 = S.substring(i, j);

                if (s2.length() >= 10)
                    continue;

                String rest = S.substring(j);
                if (rest.equalsIgnoreCase(""))
                    continue;

                if ((s2.length() != 1 && s2.startsWith("0")))
                    continue;

                List<Integer> temp = new LinkedList<>();
                temp.add(Integer.parseInt(s1));
                temp.add(Integer.parseInt(s2));
                if (check(temp, rest)) {
                    return temp;
                }
            }
        }

        return res;
    }

    public boolean check(List<Integer> temp, String remain) {
        int f = 0;
        int n = 1;
        int i = 0;
        int len = remain.length();
        while (i < len) {
            int f1 = temp.get(f);
            int f2 = temp.get(n);
            String next = Integer.toString(f1 + f2);
            if (i + next.length() <= len && remain.startsWith(next, i)) {
                i += next.length();
                temp.add(f1 + f2);
                f = n;
                n++;
            } else
                return false;

        }
        return true;
    }

    public String compute(String s1, String s2) {
        int t1 = Integer.parseInt(s1);
        int t2 = Integer.parseInt(s2);
        return Integer.toString(t1 + t2);
    }

}
