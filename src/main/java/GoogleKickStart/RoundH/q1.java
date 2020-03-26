package GoogleKickStart.RoundH;/*
 * @author: Robert
 * @date:  2020/2/27/027
 * @description:
 */

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class q1 {

    static Scanner scanner;
    static int pmax = 1;

    public static void main(String[] args) throws FileNotFoundException {

        scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        scanner.nextLine();
        HashMap<Integer, int[]> hm = new HashMap<>();
        for (int i = 1; i <= caseNum; i++) {
            int tn = scanner.nextInt();
            scanner.nextLine();
            int[] ans = new int[tn];
            for (int j = 0; j < tn; j++) {
                ans[j] = scanner.nextInt();
            }
            hm.put(i, ans);
            scanner.nextLine();
        }
        for (int i : hm.keySet())
            System.out.println(solve(i, hm.get(i).length, hm.get(i)));
    }

    private static String solve(int i, int n, int[] nums) {
        // go to the next line
        int[] ans = new int[n];
        pmax = 1;
        for (int k = 0; k < n; k++) {
            ans[k] = check(k, pmax, nums);
            if (ans[k] > pmax)
                pmax = ans[k];
        }
        StringBuffer sb = new StringBuffer();
        for (int num : ans)
            sb.append(num + " ");
        String t = sb.toString();
        return String.format("Case #%d: %s", i, t.substring(0, t.length() - 1));
    }

    private static int check(int i, int pmax, int[] nums) {
        int t = i + 1;
        while (t >= 1) {
            int cnt = 0;
            for (int k = 0; k <= i; k++)
                if (nums[k] >= t)
                    cnt++;
            if (cnt >= t)
                return Math.max(t, pmax);
            t--;
            if (t <= pmax)
                return pmax;
        }
        return 1;
    }

}
