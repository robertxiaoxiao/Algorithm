package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

import java.util.*;

public class q987 {

    static class pair {
        int cx;
        int val;

        public pair(int cx, int val) {
            this.cx = cx;
            this.val = val;
        }
    }

    public List<List<Integer>> verticalTraversalUsingpair(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        HashMap<Integer, List<pair>> hm = new HashMap<>();
        helperUsingPair(hm, root, 0, 0);
        List<Integer> keys = new LinkedList<>();
        for (int k : hm.keySet())
            keys.add(k);
        Collections.sort(keys);

        for (int key : keys) {
            List<Integer> temp = new LinkedList<>();
            List<pair> pairs = hm.get(key);
            Collections.sort(pairs, new Comparator<pair>() {
                @Override
                public int compare(pair o1, pair o2) {
                    if (o1.cx == o2.cx)
                        return o1.val - o2.val;
                    return o1.cx - o2.cx;
                }
            });
            for (pair p : pairs)
                temp.add(p.val);
            ans.add(temp);
        }
        return ans;
    }

    public void helperUsingPair(HashMap<Integer, List<pair>> hm, TreeNode root, int tx, int cy) {
        if (root == null)
            return;
        int ty = cy + 1000;
        if (!hm.containsKey(ty))
            hm.put(ty, new LinkedList<>());
        helperUsingPair(hm, root.left, tx + 1, cy - 1);
        hm.get(ty).add(new pair(tx, root.val));
        helperUsingPair(hm, root.right, tx + 1, cy + 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        List<List<Integer>> ans = new LinkedList<>();
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        helper(hm, root, 0, 0);
        List<Integer> keys = new LinkedList<>();
        for (int k : hm.keySet())
            keys.add(k);
        Collections.sort(keys);

        for (int key : keys) {
            List<Integer> temp = new LinkedList<>();
            List<Integer> pairs = hm.get(key);
            Collections.sort(pairs, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int cx1 = o1 / 1000;
                    int cv1 = o1 % 1000;
                    int cx2 = o2 / 1000;
                    int cv2 = o2 % 1000;
                    if (cx1 == cx2)
                        return cv1 - cv2;
                    return cx1 - cx2;
                }
            });
            for (int p : pairs)
                temp.add(p % 1000);
            ans.add(temp);
        }
        return ans;
    }

    public void helper(HashMap<Integer, List<Integer>> hm, TreeNode root, int tx, int cy) {
        if (root == null)
            return;
        int ty = cy + 1000;
        if (!hm.containsKey(ty))
            hm.put(ty, new LinkedList<>());
        helper(hm, root.left, tx + 1, cy - 1);
        hm.get(ty).add(tx * 1000 + root.val);
        helper(hm, root.right, tx + 1, cy + 1);
    }

}
