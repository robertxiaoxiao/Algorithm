package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/18/018
 * @description:
 */

import org.apache.tomcat.util.descriptor.web.MessageDestinationRef;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class q241 {

    public List<Integer> diffWaysToComputeUsingdfs(String input) {
        List<Integer> res = new LinkedList<>();
        List<Integer> nums = new LinkedList<>();
        List<Character> ops = new LinkedList<>();
        parseInput(nums, ops, input);
        dfs(nums, ops, res, -1);
        return res;
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));

                for (Integer lnum : left)
                    for (Integer rnum : right) {
                        switch (c) {
                            case '+':
                                res.add(lnum + rnum);
                            case '-':
                                res.add(lnum - rnum);
                            case '*':
                                res.add(lnum * rnum);
                        }
                    }
            }
        }

        if(res.size()==0)
            res.add(Integer.parseInt(input)) ;
        return  res ;
    }

    public void parseInput(List<Integer> nums, List<Character> ops, String temp) {
        int n = temp.length();
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (temp.charAt(i) == '-' || temp.charAt(i) == '+' || temp.charAt(i) == '*') {
                nums.add(num);
                ops.add(temp.charAt(i));
                num = 0;
            } else {
                num = num * 10 + temp.charAt(i) - '0';
            }
        }
        nums.add(num);
    }

    public void dfs(List<Integer> nums, List<Character> ops, List<Integer> res, int preIdx) {
        if (nums.size() == 1) {
            res.add(nums.get(0));
            return;
        }

        int n = ops.size();
        for (int i = 0; i < n; i++) {

            // why the method can jump the duplicate situation
            // to get rid of duplication because 2*4+3*5+4*6 , if there exists multiply op,
            // (a*b)*c  a*(b*c)  (a*b)+(b*c) ==(a*b)+(b*c)  1+1+1+1
            if (preIdx - i >= 2)
                continue;

            System.out.printf("%d  %d", preIdx, i);
            int num1 = nums.get(i);
            int num2 = nums.get(i + 1);

            char op = ops.get(i);
            int curres = 0;
            if (op == '-')
                curres = num1 - num2;
            else if (op == '+')
                curres = num1 + num2;
            else if (op == '*')
                curres = num1 * num2;

            nums.set(i, curres);
            nums.remove(i + 1);
            ops.remove(i);
            dfs(nums, ops, res, i);
            nums.set(i, num1);
            nums.add(-1);
            for (int j = n; j > i + 1; j--)
                nums.set(j, nums.get(j - 1));
            nums.set(i, num1);
            nums.set(i + 1, num2);
            ops.add('#');
            for (int j = n - 1; j > i; j--)
                ops.set(j, ops.get(j - 1));
            ops.set(i, op);
        }
    }


//    public void dfs(String temp, List<Integer> res) {
//        if (check(temp)) {
//            res.add(Integer.parseInt(temp));
//            return;
//        }
//        int last = -1;
//        int cur = -1;
//        int next = -1;
//        for (int i = 0; i < temp.length(); i++) {
//            if (temp.charAt(i) == '-' || temp.charAt(i) == '+' || temp.charAt(i) == '*') {
//                cur = i;
//                int j = i + 1;
//                next = j;
//                while (j < temp.length()) {
//                    if (temp.charAt(j) == '-' || temp.charAt(j) == '+' || temp.charAt(j) == '*') {
//                        next = j;
//                        break;
//                    }
//                    j++;
//                }
//                if (next == i + 1)
//                    next = temp.length();
//                // 2*-1;
//                System.out.println(temp.substring(last + 1, next));
//                int curres = compute(temp.substring(last + 1, next));
//                StringBuffer sb = new StringBuffer();
//                sb.append(temp.substring(0, last + 1) + curres + temp.substring(next));
//                dfs(sb.toString(), res);
//            }
//            last = cur;
//        }
//    }
//    public int compute(String s) {
//        if (s.charAt(0) == '-')
//            return Integer.parseInt(s);
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '-')
//                return Integer.parseInt(s.substring(0, i)) - Integer.parseInt(s.substring(i + 1));
//            else if (s.charAt(i) == '+')
//                return Integer.parseInt(s.substring(0, i)) + Integer.parseInt(s.substring(i + 1));
//            else if (s.charAt(i) == '*')
//                return Integer.parseInt(s.substring(0, i)) * Integer.parseInt(s.substring(i + 1));
//        }
//        return Integer.parseInt(s);
//    }
//    // check whether it is the final num
//    public static boolean check(String s) {
//        int i = 0;
//        int n = s.length();
//        while (i < n) {
//            if (s.charAt(i) == '*' || s.charAt(i) == '+' || (i != 0 && s.charAt(i) == '-'))
//                return false;
//            i++;
//        }
//        return true;
//    }

}
