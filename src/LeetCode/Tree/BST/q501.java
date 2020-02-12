package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/12/012
 * @description:
 */

import java.util.*;

public class q501 {


    public void inorder(TreeNode root, Map<Integer, Integer> m) {
        if (root == null) return;
        inorder(root.left, m);
        m.put(root.val, m.getOrDefault(root.val, 0) + 1);
        inorder(root.right, m);
    }

    public int[] findModeInorder(TreeNode root) {
        if (root == null)
            return new int[]{};
        Map<Integer, Integer> m = new HashMap<>();
        inorder(root, m);
        List<Integer> l = new ArrayList<>();
        int max = Collections.max(m.values());
        for (Map.Entry<Integer, Integer> e : m.entrySet())
            if (e.getValue() == max)
                l.add(e.getKey());
        int x[] = new int[l.size()];
        for (int i = 0; i < l.size(); i++)
            x[i] = l.get(i);
        return x;
    }


    private Integer prev;
    private int maxCount;
    private List<Integer> ans;
    private int curCount;

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        maxCount = 0;
        curCount = 0;
        prev = null;
        ans = new ArrayList<>();
        visit(root);

        int[] ret = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) ret[i] = ans.get(i);
        return ret;
    }

    private void visit(TreeNode root) {
        if (root == null) return;
        visit(root.left);

        if (prev == null || prev != root.val) {
            prev = root.val;
            curCount = 1;
        } else {
            curCount++;
        }

        if (curCount > maxCount) {
            ans.clear();
            maxCount = curCount;
        }
        if (curCount == maxCount) {
            ans.add(root.val);
        }

        visit(root.right);
    }

    public int[] findModeError(TreeNode root) {
        List<Integer> nums = new LinkedList<>();
        inorder(root, nums);
        int cnt = 1;
        int max = -1;
        List<Integer> ans = new LinkedList<>();
        for (int i = 0; i < nums.size(); i++) {

            int j = i;
            while (j + 1 < nums.size() && nums.get(j) == nums.get(j + 1)) {
                j++;
            }
            cnt = j - i + 1;
            if (cnt > max) {
                max = cnt;
                ans.clear();
                ans.add(nums.get(i));
            } else if (cnt == max) {
                ans.add(nums.get(i));
            }
            cnt = 1;
        }
        int[] res = new int[ans.size()];
        int idx = 0;
        for (int i : ans)
            res[idx++] = i;
        return res;
    }

    public void inorder(TreeNode root, List<Integer> m) {
        if (root == null)
            return;

        inorder(root.left, m);
        m.add(root.val);
        inorder(root.right, m);
    }
}
