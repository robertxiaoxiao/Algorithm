package LeetCode.DP;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class q1187 {

    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Arrays.sort(arr2);
        hm = new HashMap<>();
        int ans = dfs(arr1, arr2, 0, -1);
        return ans == 3000 ? -1 : ans;
    }

    static class Pair {
        int l1, l2;

        public Pair(int l1, int l2) {
            this.l1 = l1;
            this.l2 = l2;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(l1) * 37 + Integer.hashCode(l2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return l1 == pair.l1 &&
                    l2 == pair.l2;
        }

    }

    static HashMap<Pair, Integer> hm;

    public int dfs(int[] arr1, int[] arr2, int i, int pre) {

        if (i == arr1.length)
            return 0;

        Pair pair = new Pair(i, pre);
        if (hm.containsKey(pair))
            return hm.get(pair);

        int ans = 0;
        int k = upperbound(arr2, pre);
        if (arr1[i] <= pre) {
            if (k == arr2.length)
                ans = 3000;
            else
                ans = 1 + dfs(arr1, arr2, i + 1, arr2[k]);
        } else {
            if (k == arr2.length || arr2[k] >= arr1[i])
                ans = dfs(arr1, arr2, i + 1, arr1[i]);
            else
                ans = Math.min(dfs(arr1, arr2, i + 1, arr1[i]), dfs(arr1, arr2, i + 1, arr2[k]) + 1);
        }


        hm.put(pair, ans);
        System.out.printf("%d %d %d\r\n", pre, arr2[k], ans);
        return ans;
    }

    //[l,r) 左闭右开
    public static int upperbound(int[] arr, int k) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + r >> 1;
            if (arr[mid] > k)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }


    public int dfs(int[] arr1, int[] arr2, boolean[] visited, int i, int cnt) {
        if (i == arr1.length)
            return cnt;

        int ans = Integer.MAX_VALUE / 2;

        if (i == 0 || arr1[i] > arr1[i - 1])
            ans = dfs(arr1, arr2, visited, i + 1, cnt);
        if (i >= 1) {
            int preval = arr1[i];
            for (int k = upperbound(arr2, arr1[i - 1]); k < arr2.length; k++) {
                if (visited[k])
                    continue;
                arr1[i] = arr2[k];
                visited[k] = true;
                ans = Math.min(dfs(arr1, arr2, visited, i, cnt + 1), ans);
                visited[k] = false;
                arr1[i] = preval;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        HashMap<Pair, Integer> hm = new HashMap<>();
        Pair p1 = new Pair(1, 1);
        hm.put(p1, 1);
        Pair p2 = new Pair(1, 1);
        System.out.println(hm.containsKey(p2));

        int[] nums = new int[10];
        for (int i = 0; i < 10; i++)
            nums[i] = i;
        System.out.println(nums.length);
        System.out.println(upperbound(nums, 8));
        System.out.println(upperbound(nums, 9));
    }

}
