package LeetCode.SlidingWindows;

public class q407 {

    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        int[][] up = new int[n][m];
        int[][] down = new int[n][m];
        int[][] right = new int[n][m];
        int[][] left = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j >= 0; j--) {
                right[i][j] = j == m - 1 ? heightMap[i][j] : Math.max(heightMap[i][j], right[i][j + 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                left[i][j] = j == 0 ? heightMap[i][j] : Math.max(heightMap[i][j], left[i][j - 1]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                up[j][i] = j == 0 ? heightMap[j][i] : Math.max(heightMap[j][i], up[j - 1][i]);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                down[j][i] = j == n - 1 ? heightMap[j][i] : Math.max(heightMap[j][i], down[j + 1][i]);
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int min = Math.min(Math.min(down[i][j], up[i][j]), Math.min(right[i][j], left[i][j]));
                if (min > heightMap[i][j])
                    ans += min - heightMap[i][j];
            }
        }

        return ans;

        /*
        for (int i = 0; i < n; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = m - 1; j >= 0; j--) {
                int max = j;
                if (stack.isEmpty())
                    right[i][j] = m;
                else {
                    if (heightMap[i][max] < heightMap[i][stack.peek()]) {
                        max = stack.peek();
                        right[i][j] = max;
                    } else
                        right[i][j] = m;
                }
                stack.push(max);
            }
        }
        for (int i = 0; i < n; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < m; j++) {
                int max = j;
                if (stack.isEmpty())
                    left[i][j] = -1;
                else {
                    if (heightMap[i][max] < heightMap[i][stack.peek()]) {
                        max = stack.peek();
                        left[i][j] = max;
                    } else
                        left[i][j] = -1;
                }
                stack.push(max);
            }
        }
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = n - 1; j >= 0; j--) {
                int max = j;
                if (stack.isEmpty())
                    down[j][i] = n;
                else {
                    if (heightMap[max][i] < heightMap[stack.peek()][i]) {
                        max = stack.peek();
                        down[j][i] = max;
                    } else
                        down[j][i] = n;
                }
                stack.push(max);
            }
        }
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < n; j++) {
                int max = j;
                if (stack.isEmpty())
                    up[j][i] = -1;
                else {
                    if (heightMap[max][i] < heightMap[stack.peek()][i]) {
                        max = stack.peek();
                        up[j][i] = max;
                    } else
                        up[j][i] = -1;
                }
                stack.push(max);
            }
        }
        */


//        int ans = 0;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                int cd = down[i][j];
//                int cu = up[i][j];
//                int cr = right[i][j];
//                int cl = left[i][j];
//                if (cd == n || cu == -1 || cr == m || cl == -1)
//                    continue;
//                int min = Math.min(Math.min(heightMap[cd][j], heightMap[cu][j]), Math.min(heightMap[i][cl], heightMap[i][cr]));
//
//                if (min > heightMap[i][j])
//                    ans += min - heightMap[i][j];
//
//            }
//        }
//        return ans;
    }
}
