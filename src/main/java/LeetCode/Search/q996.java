package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/14/014
 * @description:
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class q996 {

    static int resCnt = 0;

    // Notice :non -negative arr ,zero edge must be considered !!!!
    public static int numSquarefulPerms(int[] A) {
        resCnt =0;
        int n = A.length;
        Arrays.sort(A);
        List<Integer> temp = new LinkedList<>();
        HashSet<String> seen = new HashSet<>();
        boolean[] used = new boolean[A.length];
        int pre = 0;
        dfs(A, 0, temp, pre, seen, used);
        return resCnt;
    }


    public static void dfs(int[] A, int cnt, List<Integer> temp, int pre, HashSet<String> seen, boolean[] used) {

        if (cnt == A.length) {
            StringBuffer sb = new StringBuffer();
            for (int i : temp) {
                sb.append(i);
            }
            String str = sb.toString();
            if (seen.contains(str))
                return;
            seen.add(str);
            ++resCnt;

        }

        for (int i = 0; i < A.length; i++) {
            if (used[i])
                continue;

            if (i - 1 >= 0 && A[i] == A[i - 1] && !used[i - 1])
                continue;
            if (cnt > 0 && !checkSquare(A[i] + pre))
                continue;

            used[i] = true;
            temp.add(i);
            dfs(A, cnt + 1, temp, A[i], seen, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }

    private static boolean checkSquare(int i) {
        if (i == 0)
            return true;

        int sqr = (int) Math.sqrt(i);
        if (sqr * sqr == i)
            return true;

        return false;
    }
    public static void main(String[] args) {
        int[] a = {2,2,2};
        System.out.println(numSquarefulPerms(a));
    }


}
