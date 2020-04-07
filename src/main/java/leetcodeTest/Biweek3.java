package leetcodeTest;

import java.util.*;

public class Biweek3 {

    public int countLargestGroup(int n) {
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int k = getval(i);
            if (!hm.containsKey(k))
                hm.put(k, new LinkedList<>());
            hm.get(k).add(i);
        }
        int max = 1;
        int cnt = 0;
        for (int k : hm.keySet()) {
            int csize = hm.get(k).size();
            if (csize > max) {
                max = csize;
                cnt = 1;
            } else if (csize == max) {
                cnt++;
            }
        }
        System.out.println(max);
        return cnt;
    }

    public static int getval(int i) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        return sum;
    }


    public boolean canConstruct(String s, int k) {
        int[] chars = new int[26];
        int n = s.length();
        if (k == n)
            return true;
        if (k > n)
            return false;

        for (char c : s.toCharArray())
            chars[c]++;

        int odd = 0;
        for (int i : chars) {
            if (i != 0 && i % 2 == 1)
                odd++;
        }
        if (odd > k)
            return false;
        return true;


    }

    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {

        int[][] nodes = {{x1, y1}, {x2, y2}, {x1, y2}, {x2, y2}};
        int maxx = Math.max(x1, x2);
        int minx = Math.min(x1, x2);
        int maxy = Math.max(y1, y2);
        int miny = Math.min(y1, y2);
        for (int[] node : nodes) {
            if (check(radius, x_center, y_center, node[0], node[1]))
                return true;
            int x = node[0];
            int y = node[1];
            if (Math.abs(x - x_center) <= radius && (y_center <= maxy && y_center >= miny))
                return true;
            if (Math.abs(y - y_center) <= radius && (x_center <= maxx && x_center >= minx))
                return true;
        }

        if (x_center <= maxx && x_center >= minx && y_center <= maxy && y_center >= miny)
            return true;

        return false;
    }

    public boolean check(int radius, int x_center, int y_center, int i, int j) {
        return (i - x_center) * (i - x_center) + (j - y_center) * (j - y_center) <= radius * radius;
    }


    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int max = 0;
        for (int k = 0; k < n; k++) {
            int sum = 0;
            for (int j = k; j < n; j++)
                sum += satisfaction[j] * (j - k + 1);
            if (sum > max)
                max = sum;
        }

        return max;
    }

}
