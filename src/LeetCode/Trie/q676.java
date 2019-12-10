package LeetCode.Trie;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */


import java.util.HashMap;
import java.util.HashSet;

/*
###NOTICE
 trie  can be implemented by hashmap;
 */
public class q676 {
    static class treeNode {
        treeNode[] childnodes = new treeNode[26];
        boolean isend = false;
        char c;

        public treeNode(char c) {
            this.c = c;
        }
    }

    class MagicDictionary {
        treeNode root;

        HashMap<Integer, HashSet<String>> hm = new HashMap<>();

        /**
         * Initialize your data structure here.
         */
        public MagicDictionary() {
            root = new treeNode('#');
        }

        /**
         * Build a dictionary through a list of words
         */
        public void buildDict(String[] dict) {
//            for (String word : dict) {
//                int len = word.length();
//                if (!hm.containsKey(len))
//                    hm.put(len, new HashSet<>());
//                hm.get(len).add(word);
//            }

            for (String word : dict) {
                treeNode temp = root;
                for (char c : word.toCharArray()) {
                    if (temp.childnodes[c - 'a'] == null)
                        temp.childnodes[c - 'a'] = new treeNode(c);
                    temp = temp.childnodes[c - 'a'];
                }
                temp.isend = true;
            }
        }

        /**
         * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
         */
        public boolean search(String word) {
//            int len = word.length();
//            if (!hm.containsKey(len))
//                return false;
//
//            HashSet<String> temp = hm.get(len);
//            for(String  s :temp)
//            {
//                int cnt =0;
//                for(int i=0;i<len;i++)
//                {
//                    if(s.charAt(i)!=word.charAt(i))
//                        cnt++ ;
//                    if(cnt>1)
//                        break;
//                }
//                if(cnt==1)
//                    return true ;
//            }
//            return false ;

            treeNode temp = root;
            int i = 0;

            for (char c : word.toCharArray()) {
                if (temp.childnodes[c - 'a'] == null) {
                    for (treeNode t : temp.childnodes) {
                        if (t != null) {
                            if (i + 1 < word.length() && dfs(word.substring(i + 1, word.length()), t))
                                return true;
                        }
                    }
                    return false;
                } else {
                    temp = temp.childnodes[c - 'a'];
                    for (treeNode t : temp.childnodes) {
                        if (t != null) {
                            if (i + 1 < word.length() && dfs(word.substring(i + 1, word.length()), t))
                                return true;
                        }
                    }
                }
                i++;
            }
            return false;
        }

        public boolean dfs(String word, treeNode root) {
            treeNode temp = root;
            for (char c : word.toCharArray()) {
                if (temp.childnodes[c - 'a'] == null)
                    return false;
                temp = temp.childnodes[c - 'a'];
            }
            if (temp.isend)
                return true;
            return false;
        }
    }


}
