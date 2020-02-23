package LeetCode.Trie;/*
 * @author: Robert
 * @date:  2020/2/20/020
 * @description:
 */

import org.eclipse.jdt.internal.compiler.ast.NullLiteral;

public class Trie {
    static class TreeNode {
        boolean is_Word;
        TreeNode[] childs;

        public TreeNode() {
            is_Word = false;
            childs = new TreeNode[26];
        }
    }

    TreeNode root;

    public Trie() {
        root = new TreeNode();
    }

    public void insert(String s) {
        TreeNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (p.childs[ch - 'a'] == null)
                p.childs[ch - 'a'] = new TreeNode();
            p = p.childs[ch - 'a'];
        }
        p.is_Word = true;
    }

    public boolean searchWord(String s) {
        TreeNode p = find(s);
        return p != null && p.is_Word;
    }

    public boolean startWith(String s) {
        TreeNode p = find(s);
        return p != null;
    }

    private TreeNode find(String s) {
        TreeNode p = root;
        for (char c : s.toCharArray()) {
            if (p.childs[c - 'a'] == null)
                return null;
            p = p.childs[c - 'a'];
        }
        return p;
    }

}
