package leetcodeTest.Week3;/*
 * @author:
 * @date:  2019/11/25/025
 * @description:
 */

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
            1  binaryserach
            2  Trie tree
            3  BFS
 */
public class q3 {
    private static class trieNode {
        char C;
        trieNode[] next = new trieNode[26];
        List<String> list = new LinkedList<>();

        public trieNode(char c) {
            C = c;
        }
    }

    static trieNode initTreeNode(String searchSword) {
        trieNode root = new trieNode('#');
        trieNode temp = root;
        for (char word : searchSword.toCharArray()) {
            temp.next[word - 'a'] = new trieNode(word);
            temp = temp.next[word - 'a'];
        }
        return root;
    }

    static void addNode(trieNode root, String node) {
        trieNode temp = root;
        if (root == null)
            return;
        for (char c : node.toCharArray()) {
            temp = temp.next[c - 'a'];
            if (temp != null) {
                if (temp.list.size() < 3)
                    temp.list.add(node);
            } else
                return;
        }
    }

    public List<List<String>> suggestedProductsUsingTrieNode(String[] products, String searchWord) {

        // collection.sort : sort in list
        Arrays.sort(products);
        List<List<String>> res = new LinkedList<>();
        trieNode root = initTreeNode(searchWord);
        for (String product : products)
            addNode(root, product);

        trieNode temp = root;
        for (char c : searchWord.toCharArray()) {
            res.add(temp.next[c - 'a'].list);
            temp = temp.next[c - 'a'];
        }
        return res;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new LinkedList<>();
        Arrays.sort(products);
        for (int i = 0; i < searchWord.length(); i++) {
            System.out.println(searchWord.length());
            int cur = binarySearchPrefix(products, searchWord.substring(0, i + 1));
            System.out.println(cur);
            List<String> temp = new LinkedList<>();
            if (cur != -1) {
                int cnt = 0;
                while (cur < products.length && cnt < 3)
                    if (judge(products[cur], searchWord.substring(0, i + 1))) {
                        temp.add(products[cur++]);
                        cnt++;
                    } else
                        break;
            }
            res.add(temp);
        }

        return res;
    }

    public int binarySearchPrefix(String[] products, String prefix) {
        int i = 0;
        int j = products.length - 1;
        while (i <= j) {
            // left side
            int mid = (i + j) / 2;
            if (!judge(products[mid], prefix)) {
                if (judgeDir(products[mid], prefix) == -1)
                    i = mid + 1;
                else if (judgeDir(products[mid], prefix) == 1)
                    j = mid - 1;
            }

            if (judge(products[mid], prefix)) {
                int cur = mid;
                while (mid >= i && judge(products[mid], prefix)) {
                    cur = mid;
                    mid--;
                }
                if (cur >= i) {
                    i = cur;
                    break;
                }
            }
        }
        if (i > j)
            return -1;
        if (judge(products[i], prefix))
            return i;
        else
            return -1;
    }

    public static int judgeDir(String product, String prefix) {

        int prelen = Math.min(product.length(), prefix.length());
        for (int i = 0; i < prelen; i++) {
            if (product.charAt(i) > prefix.charAt(i))
                return 1;
            else if (product.charAt(i) < prefix.charAt(i))
                return -1;
        }
        return 0;
    }

    public static boolean judge(String product, String prefix) {
        int len = product.length();
        int prelen = prefix.length();
        if (len < prelen)
            return false;
        for (int i = 0; i < prelen; i++)
            if (product.charAt(i) != prefix.charAt(i))
                return false;

        return true;
    }


}
