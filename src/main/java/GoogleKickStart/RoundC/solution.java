package GoogleKickStart.RoundC;

import java.util.*;

public class solution {

    static int n;
    static int K;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] ans = new int[N + 1];
        for (int cases = 1; cases <= N; cases++) {
            scanner.nextLine();
            n = scanner.nextInt();
            scanner.nextLine();
            int[] temp = new int[n];
            for (int i = 0; i < n; i++)
                temp[i] = scanner.nextInt();
            ans[cases] = solve(temp);
        }
        for (int i = 1; i <= N; i++) {
            System.out.printf("Case #%d: %s\n", i, ans[i]);
        }
    }

//    public static String solve(String[] matrix, int n, int k) {
//        char[][] ch = new char[n][k];
//        ans = new LinkedList<>();
//        HashSet<Character> hset = new HashSet<>();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < k; j++) {
//                char cu = matrix[i].charAt(j);
//                ch[i][j] = cu;
//                hset.add(cu);
//            }
//        }
//        List<Character> rest = new LinkedList<>(hset);
//        for (Character c : rest) {
//            dfs(c, rest, hset, ch, new StringBuffer());
//        }
//
//        if (ans.size() != 0)
//            return ans.get(0);
//
//        return "-1";
//    }
//    static List<String> ans;
//    public static void dfs(char c, List<Character> rest, HashSet<Character> seen, char[][] mat, StringBuffer orders) {
//        if (check(mat, c)) {
//            seen.remove(c);
//            orders.append(c);
//            char[][] temp = mat.clone();
//            del(mat, c);
//            if (seen.size() == 0) {
//                ans.add(orders.toString());
//                return;
//            }
//            for (Character ch : rest) {
//                if (seen.contains(ch))
//                    dfs(ch, rest, seen, mat, orders);
//            }
//            recover(mat, temp);
//            orders.deleteCharAt(orders.length() - 1);
//            seen.add(c);
//        }
//    }
//    public static void recover(char[][] mat, char[][] l1) {
//        int n = mat.length;
//        int m = mat[0].length;
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < m; j++)
//                mat[i][j] = l1[i][j];
//    }
//    public static void del(char[][] mat, char c) {
//        int n = mat.length;
//        int m = mat[0].length;
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < m; j++) {
//                if (mat[i][j] == c)
//                    mat[i][j] = '#';
//            }
//    }
//    public static boolean check(char[][] mat, char c) {
//        int n = mat.length;
//        int m = mat[0].length;
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < m; j++) {
//                if (mat[i][j] == c) {
//                    if (i + 1 < n) {
//                        if (mat[i + 1][j] == c || mat[i + 1][j] == '#')
//                            continue;
//                        else {
//                            return false;
//                        }
//                    }
//                }
//            }
//        return true;
//    }
//
    /*
    public static int solve(int[] arr, int k) {
        int n = arr.length;
        int cnt = 0;
        for (int i = 0; i < n; ) {
            if (arr[i] == k) {
                int prei = i;
                int cur = k;
                boolean flag = true;
                while (i < n && cur >= 1) {
                    if (arr[i] != cur) {
                        flag = false;
                        break;
                    }
                    cur--;
                    i++;
                }
                if (flag && cur == 0)
                    cnt++;
                else
                    i = prei + 1;
            }else
                i++;
        }
        return cnt;
    }
*/

    public static int solve(int[] arr) {
        int n = arr.length;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            presum[i] = presum[i - 1] + arr[i - 1];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (check(presum[j + 1] - presum[i])) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static boolean check(int k) {
        double x = Math.sqrt(k);
        return (int) x * (int) x == k;
    }


}
