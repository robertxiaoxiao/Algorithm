package LeetCode.Graph.UFS;/*
 * @author: Robert
 * @date:  2020/1/13/013
 * @description:
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class q990 {

    class ufs {
        HashMap<Character, Character> hm = new HashMap<>();

        public char findParent(char c) {
            if (!hm.containsKey(c)) {
                hm.put(c, c);
                return c;
            }
            char p = hm.get(c);
            if (p != c) {
                char fp = findParent(p);
                hm.put(c, fp);
                return fp;
            }
            return p;
        }

        public void union(char a, char b) {
            char pa = findParent(a);
            char pb = findParent(b);
            if (pa != pb) {
                hm.put(pa, pb);
            }
        }
    }

    public boolean equationsPossible(String[] equations) {
        ufs uset = new ufs();
        List<String> ue = new LinkedList<>();
        for (String es : equations) {
            char a = es.charAt(0);
            char b = es.charAt(3);
            boolean eql = es.charAt(1) == '=';
            if (!eql) {
                ue.add(es);
                continue;
            }
            char pa = uset.findParent(a);
            char pb = uset.findParent(b);
            uset.union(a, b);
        }

        for (String e : ue) {
            char a = e.charAt(0);
            char b = e.charAt(3);
            char pa = uset.findParent(a);
            char pb = uset.findParent(b);
            if (pa == pb)
                return false;
        }
        return true;
    }
}
