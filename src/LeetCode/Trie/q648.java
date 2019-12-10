package LeetCode.Trie;/*
 * @author:
 * @date:  2019/12/9/009
 * @description:
 */

import java.util.List;

public class q648 {

    static class treeNode {
        treeNode[] childnodes = new treeNode[26];
        boolean isend = false;
        char c;

        public treeNode(char c) {
            this.c = c;
        }
    }

    static class Trie {
        treeNode root;

        public Trie() {
            this.root = new treeNode('#');
        }

        public void addNode(String word) {
            treeNode temp = root;
            for (char c : word.toCharArray()) {
                if (temp.childnodes[c - 'a'] == null)
                    temp.childnodes[c - 'a'] = new treeNode(c);
                temp = temp.childnodes[c - 'a'];
            }
            temp.isend = true;
        }

        public String findRoot(String word) {
            treeNode temp = root;
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (temp.childnodes[c - 'a'] != null) {
                    temp = temp.childnodes[c - 'a'];
                    sb.append(temp.c);
                    if (temp.isend)
                        return sb.toString();
                } else
                    return word;
            }
            return word;
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie trie = new Trie();
        for (String word : dict)
            trie.addNode(word);

        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words)
            sb.append(trie.findRoot(word) + " ");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {

        String s1 = new String("hello");
        String s2 = "hello";
        String s3 = s1.intern();
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
    }

}
