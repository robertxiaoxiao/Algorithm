package LeetCode.Greedy;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Ac187 {

    static int N = 55;
    static int[] up = new int[N];
    static int[] down = new int[N];
    static int n;
    static int ans;
    static int[] nums;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int m = 1;
        List<Integer> anslist = new LinkedList<>();
        while (true) {
            m = in.nextInt();
            if (m == 0)
                break;
            in.nextLine();
            int[] ns = new int[m];
            for (int i = 0; i < m; i++)
                ns[i] = in.nextInt();

            in.nextLine();
            int t = solve(ns);
            anslist.add(t);
        }
        for (int i : anslist)
            System.out.printf("%d\n", i);
    }

    public static int solve(int[] ns) {
        ans = Integer.MAX_VALUE;
        n = ns.length;
        nums = ns;
        dfs(0, 0, 0);
        return ans;
    }


    public static void dfs(int u, int d, int k) {

        if (u + d >= ans)
            return;

        if (k == n) {
            if (u + d < ans)
                ans = u + d;
            return;
        }
        int i = 1;
        for (; i <= u; i++)
            if (up[i] < nums[k])
                break;
        int temp = up[i];
        up[i] = nums[k];
        dfs(Math.max(u, i), d, k + 1);
        up[i] = temp;

        i = 1;
        for (; i <= d; i++)
            if (down[i] > nums[k])
                break;

        temp = down[i];
        down[i] = nums[k];
        dfs(u, Math.max(d, i), k + 1);
        down[i] = temp;
    }
}