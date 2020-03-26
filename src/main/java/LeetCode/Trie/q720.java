package LeetCode.Trie;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class q720 {
    static class treeNode {
        treeNode[] childnodes = new treeNode[26];
        boolean isend = false;
        char c;
        int len;

        public treeNode(char c) {
            this.c = c;
        }
    }

    static class Trie {
        treeNode root;
        String cur = null;

        public Trie() {
            this.root = new treeNode('#');
        }

        public void addnode(String word) {
            treeNode temp = root;
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (temp.childnodes[c - 'a'] == null)
                    temp.childnodes[c - 'a'] = new treeNode(c);
                temp = temp.childnodes[c - 'a'];
                if (!temp.isend)
                    flag = false;
            }
            temp.isend = true;
            temp.len = word.length();
            if (cur == null) {
                cur = new String(word);
                return;
            } else if (flag) {
                if (cur.length() < word.length()) {
                    cur = new String(word);
                } else if (cur.length() == word.length())
                    cur = new String(cmp(cur, word) ? cur : word);
            }
        }
    }

    public static boolean cmp(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++)
            if (s1.charAt(i) < s2.charAt(i))
                return true;
        return false;
    }

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        Arrays.sort(words);
        for (String word : words)
            trie.addnode(word);
        return trie.cur;
    }

    public String longestWordUsingHashset(String[] words) {
        Arrays.sort(words);
        Set<String> built = new HashSet<String>();
        String res = "";
        for (String w : words) {
            if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                res = w.length() > res.length() ? w : res;
                built.add(w);
            }
        }
        return res;
    }


}
