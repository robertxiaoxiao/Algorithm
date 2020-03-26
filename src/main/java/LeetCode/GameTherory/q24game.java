package LeetCode.GameTherory;/*
 * @author: Robert
 * @date:  2020/1/3/003
 * @description:
 */

import org.apache.coyote.http2.Flags;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 double to  judge whether two nums are the same :
        Math.abs(nums.get(0) - targetnum) < 1e-6


 */
public class q24game {
    static boolean found = false;

    public boolean judgePoint24(int[] nums) {
        found = false;
        List<Double> dnums = new LinkedList<>();
        for (int num : nums)
            dnums.add(num * 1.0);
        voiddfs(dnums);
        return found;
    }

    public boolean dfs(List<Double> nums) {
        if (nums.size() == 0)
            return false;
        if (nums.size() == 1) {
            if (Math.abs(nums.get(0) - 24.0) < 1e-6) {
                return true;
            } else
                return false;
        }

        boolean flag = false;
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            double a = nums.get(i);
            for (int j = i + 1; j < n; j++) {
                double b = nums.get(j);
                List<Double> next = new LinkedList<>();
                next.addAll(Arrays.asList(a + b, a - b, b - a, a * b));
                if (a != 0)
                    next.add(b / a);
                if (b != 0)
                    next.add(a / b);

                // NOTICE : IT MUST DELETE ELEMENTS FROM END TO HEAD
                nums.remove(j);
                nums.remove(i);
                for (double nextnum : next) {
                    nums.add(nextnum);
                    flag |= dfs(nums);
                    nums.remove(nums.size() - 1);
                }
                // list.add(i,num) ;
                // IT MUST ADD ELEMENTS FROM HEAD TO END
                nums.add(i, a);
                nums.add(j, b);
            }
        }

        return flag;

    }

    public void voiddfs(List<Double> nums) {
        if (nums.size() == 0)
            return;
        if (nums.size() == 1) {
            if (Math.abs(nums.get(0) - 24.0) < 1e-6) {
                found = true;
                return;
            }
        }
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            double a = nums.get(i);
            for (int j = i + 1; j < n; j++) {
                double b = nums.get(j);
                List<Double> next = new LinkedList<>();
                next.addAll(Arrays.asList(a + b, a - b, b - a, a * b));
                if (a != 0)
                    next.add(b / a);
                if (b != 0)
                    next.add(a / b);

                // NOTICE : IT MUST DELETE ELEMENTS FORM END TO HEAD
                nums.remove(j);
                nums.remove(i);
                for (double nextnum : next) {
                    nums.add(nextnum);
                    dfs(nums);
                    nums.remove(nums.size() - 1);
                }
                nums.add(i, a);
                nums.add(j, b);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println();
    }

    // TO DO
    public double compute(String s) {
        Stack<Character> stack = new Stack<>();
        double ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '1' && ch <= '9')
                stack.push(ch);
        }

        return ans;
    }
}
