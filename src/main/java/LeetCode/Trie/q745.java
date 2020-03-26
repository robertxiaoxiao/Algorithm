package LeetCode.Trie;/*
 * @author:
 * @date:  2019/12/10/010
 * @description:
 */

import java.util.*;

public class q745 {
    public q745(String[] words) {

    }

    // sorted key-value we should use the linkedlist rather hashset ;
    // and we can get the max and min value in O(1) times;

    HashMap<String, List<Integer>> prefixs = new HashMap<>();
    HashMap<String, List<Integer>> suffixs = new HashMap<>();

    public void build(String[] words) {
        for (int k = 0; k < words.length; k++) {
            String word = words[k];
            int n = word.length();
            for (int i = 0; i <= n; i++) {
                String prefix = word.substring(0, i);
                String suffix = word.substring(n - i);
                List<Integer> tempprefix = prefixs.get(prefix);
                if (tempprefix == null)
                    tempprefix = new ArrayList<>();
                tempprefix.add(k);
                prefixs.put(prefix, tempprefix);

                List<Integer> tempsuffix = suffixs.get(suffix);
                if (tempsuffix == null)
                    tempsuffix = new ArrayList<>();
                tempsuffix.add(k);
                suffixs.put(suffix, tempsuffix);
            }
        }
    }

    public List<Integer> getsuffix(String word) {
        return suffixs.get(word);
    }

    public List<Integer> getprefix(String word) {
        return prefixs.get(word);
    }

    public int f(String prefix, String suffix) {
        if (!prefixs.containsKey(prefix) || !suffixs.containsKey(suffix))
            return -1;
        List<Integer> p = getprefix(prefix);
        List<Integer> s = getsuffix(suffix);
        int i = p.size() - 1, j = s.size() - 1;
        while (i >= 0 && j >= 0) {
            if (p.get(i) < s.get(j)) j--;
            else if (p.get(i) > s.get(j)) i--;
            else return p.get(i);
        }
        return -1;
    }

    public static void main(String[] args) {
    LinkedList l=new LinkedList();
    }
}
