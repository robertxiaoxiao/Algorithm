package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/18/018
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

import static LeetCode.Search.q301.check;

public class q282 {

    public List<String> addOperators(String num, int target) {
        List<String> res = new LinkedList<>();

        // deal with the overflow case
        if (target == Integer.MIN_VALUE)
            return res;

        int opsMax = num.length() - 1;
        for (int ops = opsMax; ops >= 0; ops--) {
            dfs(ops, 0, target, num, res);
        }
        return res;
    }

    static char[] ops = {'+', '-', '*'};

    public void dfs(int opsMax, int cnt, int target, String cur, List<String> res) {

        if (cnt == cur.length())
            return;

        if (opsMax == 0) {
            if (check(cur) && compute(cur) == target)
                res.add(cur);
            return;
        }

        for (int i = cnt + 1; i < cur.length(); i++) {
            String s1 = cur.substring(0, i);
            String s2 = cur.substring(i);
            System.out.println(s1);
            for (int k = 0; k < 3; k++) {
                StringBuffer sb = new StringBuffer();
                sb.append(s1 + ops[k] + s2);
                dfs(opsMax - 1, i + 1, target, sb.toString(), res);
            }
        }
    }

    public static boolean check(String s) {

        int last = -1;
        int cur = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                cur = i;
                String temp = s.substring(last + 1, cur);
                if (temp.length() != 1 && temp.startsWith("0"))
                    return false;
            }
            last = cur;
        }

        String lstr = s.substring(last + 1);
        if (lstr.length() != 1 && lstr.startsWith("0"))
            return false;

        return true;
    }

    public static int compute(String s) {
        int n = s.length();
        List<Integer> nums = new LinkedList<>();
        List<Character> ops = new LinkedList<>();
        int num = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else {
                ops.add(c);
                nums.add(num);
                num = 0;
            }
        }

        nums.add(num);
        for (int i = 0; i < ops.size(); i++) {
            if (ops.get(i) == '*') {
                int num1 = nums.get(i);
                int num2 = nums.get(i + 1);
                ops.remove(i);
                nums.set(i, num1 * num2);
                nums.remove(i + 1);
                i--;
            }
        }
        int ans = nums.get(0);
        for (int i = 0; i < ops.size(); i++) {
            int num2 = nums.get(i + 1);
            if (ops.get(i) == '+')
                ans += num2;
            else if (ops.get(i) == '-')
                ans -= num2;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "100*0+50*30";
        String s2 = "20+3*111";
        String s3 = "1*2*3";
        System.out.println("2147483648");
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(compute(s1));
        System.out.println(compute(s3));
    }
}
