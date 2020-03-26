package leetcodeTest.Week20;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++)
            if (index[i] == list.size())
                list.add(nums[i]);
            else
                list.add(index[i], nums[i]);

        int[] ans = new int[n];
        int idx = 0;
        for (int i : list)
            ans[idx++] = i;
        return ans;
    }

    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            int cnt = 0;
            int sum = 0;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) {
                    cnt++;
                    sum += i;
                    if (num / i > (int) Math.sqrt(num)) {
                        cnt++;
                        sum += num / i;
                    }
                }

                if (cnt > 2)
                    break;
            }

            if (cnt == 2) {
                ans += sum + 1 + num;
            }
        }
        return ans;
    }

    static int[][] rules = {{1, 3}, {0, 2}, {1, 2}, {2, 3}, {0, 1}, {0, 3}};
    static int[] dirs = {0, -1, 0, 1, 0}; //UP LEFT DOWN RIGHT

    public boolean hasValidPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            if (cx == n - 1 && cy == m - 1)
                return true;
            int croad = grid[cx][cy];
            for (int d : rules[croad - 1]) {
                int nx = cx + dirs[d + 1];
                int ny = cy + dirs[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || !check(rules[grid[nx][ny] - 1], d))
                    continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
        return false;
    }

    public static boolean check(int[] crules, int d) {
        int nextd = (d + 2) % 4;
        for (int i : crules)
            if (i == nextd)
                return true;
        return false;
    }

    public String longestPrefix(String s) {
        int n = s.length();
        int[] next = new int[n + 1];
        next[0] = -1;
        int i = 0;
        int k = -1;
        while (i < n) {
            if (k == -1 || s.charAt(i) == s.charAt(k)) {
                i++;
                k++;
                next[i] = k;
            } else {
                k = next[k];
            }
        }

        if (next[n] == 0)
            return "";
        return s.substring(0, next[n]);
    }
}
