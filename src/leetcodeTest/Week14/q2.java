package leetcodeTest.Week14;/*
 * @author: Robert
 * @date:  2020/2/9/009
 * @description:
 */

public class q2 {
    public int minSteps(String s, String t) {
        int[] s1 = new int[26];
        int[] t1 = new int[26];
        if(s.length()==0)
            return 0;
        for (int i = 0; i < s.length(); i++) {
            s1[s.charAt(i) - 'a']++;
            t1[t.charAt(i) - 'a']++;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++)
            ans += getv(s1[i], t1[i]);

        return ans / 2;
    }

    public int getv(int a, int b) {
        return a > b ? a - b : b - a;
    }
}
