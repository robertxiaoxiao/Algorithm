package LeetCode.Search;/*
 * @author: Robert
 * @date:  2019/12/17/017
 * @description:
 */

import java.util.*;

public class q212 {

    static int[] dirs = {0, 1, 0, -1, 0};

    static class treeNode {
        treeNode[] childs = new treeNode[26];
        String cur = "";
    }

    public void addNodes(String[] words, treeNode root) {
        for (String word : words) {
            treeNode temp = root;
            for (char c : word.toCharArray()) {
                if (temp.childs[c - 'a'] == null)
                    temp.childs[c - 'a'] = new treeNode();
                temp = temp.childs[c - 'a'];
            }
            temp.cur = word;
        }
    }

    public void search(int x, int y, char[][] mat, boolean[][] visited, treeNode root, List<String> res) {

        if (x < 0 || x >= mat.length || y < 0 || y >= mat[0].length)
            return;

        if (root == null || root.childs[mat[x][y] - 'a'] == null)
            return;

        if (visited[x][y])
            return;

        visited[x][y] = true;
        treeNode curNode = root.childs[mat[x][y] - 'a'];
        // all curnodes has the same reference to static pool "" ,and so we can compare string with == ,
        // more generally ,we would better use s1.equal() or s1.equalIgnoreCase();
        // s1.compareTo(s2) :compares values lexicographically and returns an integer value
        if (curNode.cur != "") {
            res.add(curNode.cur);
            curNode.cur = "";
        }

        for (int i = 0; i < 4; i++) {
            int nextx = x + dirs[i];
            int nexty = y + dirs[i + 1];

            if (nextx < 0 || nextx >= mat.length || nexty < 0 || nexty >= mat[0].length)
                continue;

            if (curNode.childs[mat[nextx][nexty] - 'a'] == null)
                continue;

            search(nextx, nexty, mat, visited, curNode, res);
        }
        visited[x][y] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        treeNode root = new treeNode();
        addNodes(words, root);
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        List<String> res = new LinkedList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                search(i, j, board, visited, root, res);
        return res;
    }

    public static List<String> findWordsOverhead(char[][] board, String[] words) {
        HashSet<String> res = new HashSet<>();
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                HashMap<String, Integer> hm = findTarget(words, board[i][j]);
                if (hm == null)
                    continue;
                dfs(i, j, n, m, board, visited, hm, res, "");
            }
        return new LinkedList<>(res);
    }

    public static HashMap<String, Integer> findTarget(String[] words, char c) {

        HashMap<String, Integer> hm = new HashMap<>();
        boolean flag = false;
        for (String word : words)
            if (word.charAt(0) == c) {
                hm.put(word, 1);
                flag = true;
            }
        if (flag)
            return hm;
        else
            return null;
    }

    public static void dfs(int x, int y, int n, int m, char[][] board, boolean[][] visited, HashMap<String, Integer> hm, HashSet<String> res, String cur) {
        if (x < 0 || y < 0 || x >= n || y >= m)
            return;

        cur += board[x][y];
        System.out.println(cur);

        if (hm.getOrDefault(cur, -1) == 1) {
            hm.put(cur, 0);
            res.add(cur);
            // notice : the output point must be thought clear ,maybe in the  final result set ,it will  contains more length result which
            // has the same prefix as before ,especially at the non-assigned-length result;
            //return ;
        }
        visited[x][y] = true;
        boolean flag = false;
        for (String word : hm.keySet())
            if (word.startsWith(cur)) {
                flag = true;
                break;
            }

        if (!flag)
            return;

        for (int i = 0; i < 4; i++) {
            int nextx = x + dirs[i];
            int nexty = y + dirs[i + 1];
            if (nextx < 0 || nexty < 0 || nextx >= n || nexty >= m || visited[nextx][nexty])
                continue;

            visited[nextx][nexty] = true;
            dfs(nextx, nexty, n, m, board, visited, hm, res, cur);
            visited[nextx][nexty] = false;
        }
    }

    public static void print(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++)
            for (int j = 0; j < visited.length; j++)
                if (visited[i][j])
                    System.out.println("x");
    }

    public static void main(String[] args) {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] words = {"oath", "pea", "eat", "rain"};
        String s="";
        System.out.println(s=="");
        System.out.println(s.equalsIgnoreCase(""));
        String t="a";
        String t1=new String("a");
        System.out.println(t=="a");
        System.out.println("a"=="a");
        System.out.println(t1=="");
        String s1="Sachin";
        String s3="Ratan";
        System.out.println("a".compareTo("b"));
        System.out.println(s1.compareTo(s3));
        //      System.out.println(findWords(board, words));
    }
}
