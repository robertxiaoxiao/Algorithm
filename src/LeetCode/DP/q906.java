package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/2/002
 * @description:
 */

public class q906 {
    public int superpalindromesInRange(String L, String R) {
        int low = (int) Math.ceil(Math.sqrt(Long.parseLong(L)));
        int high = (int) Math.floor(Math.sqrt(Long.parseLong(R)));

        // only contains 0 1 2 ;
        int ret = (3 >= low && 3 <= high) ? 1 : 0;
        ret += dfs(low, high, "");
        ret += dfs(low, high, "0");
        ret += dfs(low, high, "1");
        ret += dfs(low, high, "2");
        return ret;
    }

    public int dfs(int low, int high, String t) {

        if (t.length() > Integer.toString(high).length())
            return 0;

        int cnt = 0;
        if (!t.isEmpty() && t.charAt(0) != 0) {
            long num = Long.parseLong(t);
            if (num > high)
                return 0;
            if (num >= low && ispalindom(num * num))
                cnt++;
        }

        for (char c = '0'; c <= '9'; c++) {
            cnt += dfs(low, high, c + t + c);
        }
        return cnt;

    }


    public boolean ispalindom(long s) {
        String s1 = Long.toString(s);
        return s1.equalsIgnoreCase(new StringBuffer(s1).reverse().toString());
    }
}
