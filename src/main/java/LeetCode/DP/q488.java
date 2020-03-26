package LeetCode.DP;/*
 * @author: Robert
 * @date:  2020/1/2/002
 * @description:
 */

import java.util.HashMap;

public class q488 {
    static HashMap<Character, Integer> hands = new HashMap<>();

    public static String update(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && chs[i] == chs[j])
                j++;
            if (j - i >= 3)
                return update(s.substring(0, i) + s.substring(j));
            i = j;
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "BBBGGRRYY";
        System.out.println(update(s));
    }

    public int findMinStep(String board, String hand) {
        hands.clear();
        for (char c : hand.toCharArray())
            hands.put(c, hands.getOrDefault(c, 0) + 1);
        return helper(board);
    }

    public int helper(String board) {
        if (board.equalsIgnoreCase(""))
            return 0;
        int n = board.length();
        int i = 0;
        int min = Integer.MAX_VALUE / 2;
        while (i < n) {
            int j = i + 1;
            while (j < n && board.charAt(i) == board.charAt(j))
                j++;
            // i  j-1 j
            int b = 3 - (j - i);
            char ch = board.charAt(i);
            if (hands.getOrDefault(ch, 0) >= b) {
                hands.put(ch, hands.getOrDefault(ch, 0) - b);
                String temp = board.substring(0, i) + board.substring(j);
                System.out.println(update(temp));
                hands.put(ch, hands.getOrDefault(ch, 0) + b);
                int ans = b + helper(temp);
                if (ans >= 0)
                    min = Math.min(min, ans);
            }
            i = j;
        }
        return min == Integer.MAX_VALUE / 2 ? -1 : min;
    }

    public String updateUsingtraverse(String board) {
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(i) == board.charAt(j)) ++j;
            if (j - i >= 3) {
                board = board.substring(0, i) + board.substring(j);
                i = 0;
            } else {
                ++i;
            }
        }
        return board;
    }
}
