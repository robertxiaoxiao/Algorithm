package leetcodeTest.BiWeek1;/*
 * @author: Robert
 * @date:  2020/2/22/022
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q3 {

    public int numberOfSubstrings(String s) {
        int n = s.length(), i;
        int fira = n, firb = n, firc = n;
        long ans = 0;
        for (i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == 'a') fira = i;
            else if (s.charAt(i) == 'b') firb = i;
            else firc = i;
            int max = Math.max(fira, firb);
            max = Math.max(max, firc);
            ans += n - max;
        }
        return (int) ans;
    }

    public static int numberOfSubstringsTwopointer(String s) {
        int n = s.length();
        int j = 0;
        int ans = 0;
        boolean[] exited = new boolean[3];
        int[] ns = new int[3];
        // sliding windows
        //  s:[i      j       n]
        // substr : [i,j-1]
        // O(2N)
        for (int i = 0; i < n; i++) {
            while (j < n && !(exited[0] & exited[1] & exited[2])) {
                char ch = s.charAt(j);
                ns[ch - 'a']++;
                exited[ch - 'a'] = true;
                j++;
            }
            if (exited[0] & exited[1] & exited[2]) {
                ans += n - j + 1;
                char pre = s.charAt(i);
                ns[pre - 'a']--;
                exited[pre - 'a'] = ns[pre - 'a'] != 0;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(numberOfSubstringsTwopointer("abcabc"));
    }

}
