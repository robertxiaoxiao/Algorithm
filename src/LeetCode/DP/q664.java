package LeetCode.DP;/*
 * @author: Robert
 * @date:  2019/12/28/028
 * @description:
 */


//
public class q664 {
    public int strangePrinter(String s) {

        // dp : i start  i end at most  len seq/pre
        return helper(s, 0, s.length() - 1);
    }

    public int helper(String s, int start, int end) {
        int dif = check(s, start, end);
        if (dif == -1)
            return 1;

        int min = Integer.MAX_VALUE;
        for (int i = dif; i <= end; i++) {
            int rest = helper(s, start, i) + helper(s, i + 1, end);
            min = Math.min(min, rest);
        }
        return min;
    }

    public int check(String s, int start, int end) {

        char c = s.charAt(start);
        for (int i = start; i <= end; i++)
            if (s.charAt(i) != c)
                return i;
        return -1;
    }

}
