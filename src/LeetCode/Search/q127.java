package LeetCode.Search;/*
 * @author: Robert
 * @date:  2020/1/1/001
 * @description:
 */

import java.util.*;

public class q127 {
    //one direction bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        HashSet<String> dicts = new HashSet<>(wordList);
        if (!dicts.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                size--;
                String temp = queue.poll();
                char[] tchars = temp.toCharArray();
                int len = temp.length();
                for (int i = 0; i < len; i++) {
                    char ct = temp.charAt(i);
                    /*
                      NOTICE :  it can not be included in for loop (c!=t&&c<c')
                      it will break when c ==t ,the right expression is following :
                      for(c;c<c';c++)
                        if(c==t)
                          continue ;
                     */
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == ct)
                            continue;
                        tchars[i] = c;
                        String newword = new String(tchars);
                        tchars[i] = ct;
                        if (newword.equalsIgnoreCase(endWord))
                            return steps + 1;
                        if (dicts.contains(newword)) {
                            queue.add(newword);
                            dicts.remove(newword);
                        }
                    }
                }
            }
            steps++;
        }

        return 0;
    }

    public int ladderLengthUsingbidir(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for (String word : wordList) dict.add(word);

        if (!dict.contains(endWord)) return 0;

        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        q1.add(beginWord);
        q2.add(endWord);

        int l = beginWord.length();
        int steps = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {
            ++steps;

            if (q1.size() > q2.size()) {
                Set<String> tmp = q1;
                q1 = q2;
                q2 = tmp;
            }

            Set<String> q = new HashSet<>();

            for (String w : q1) {
                char[] chs = w.toCharArray();
                for (int i = 0; i < l; ++i) {
                    char ch = chs[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        chs[i] = c;
                        String t = new String(chs);
                        if (q2.contains(t)) return steps + 1;
                        if (!dict.contains(t)) continue;
                        dict.remove(t);
                        q.add(t);
                    }
                    chs[i] = ch;
                }
            }

            q1 = q;
        }
        return 0;
    }

    public int bidirbfs(String beginWord, String endWord, List<String> wordList) {
        Set<String> h1 = new HashSet<>();
        Set<String> h2 = new HashSet<>();
        h1.add(beginWord);
        h2.add(endWord);
        Set<String> dicts = new HashSet<>(wordList);
        int steps = 0;

        if (!dicts.contains(endWord))
            return 0;
        while (!h1.isEmpty() && !h2.isEmpty()) {
            steps++;
            if (h1.size() > h2.size()) {
                Set<String> temp = h1;
                h1 = h2;
                h2 = temp;
            }
            HashSet<String> nextset = new HashSet<>();

            for (String word : h1) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char ct = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String newword = new String(chars);
                        if (h2.contains(newword))
                            return steps + 1;

                        if (!dicts.contains(newword))
                            continue;
                        nextset.add(newword);
                        dicts.remove(newword);
                    }
                    chars[i] = ct;
                }
            }
            h1 = nextset;
        }
        return 0;
    }
}
