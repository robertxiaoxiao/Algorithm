package leetcodeTest.Week13;/*
 * @author: Robert
 * @date:  2020/2/2/002
 * @description:
 */

import BasicDStructure.Tree.TreeNode;
import LeetCode.Tree.Node;

import java.util.HashMap;

public class q3 {
    static long max = -1;

    public int maxProduct(TreeNode root) {
        max = -1;

        HashMap<TreeNode, Long> nsum = new HashMap<>();
        helper(root, root,nsum);
        return (int) (max % (1e9+7));
    }

    public void helper(TreeNode root, TreeNode cur, HashMap<TreeNode, Long> nsum) {
        if (cur == null)
            return;

        TreeNode tl = cur.left;
        TreeNode tr = cur.right;

        if (cur.left != null) {
            cur.left = null;
            long temp = (getSum(root,nsum)-getSum(cur.left,nsum)) * getSum(tl,nsum);
            if (temp > max)
                max = temp;
            cur.left = tl;
            helper(root, cur.left,nsum);
        }

        if (cur.right != null) {
            cur.right = null;
            long temp = (getSum(root,nsum)-getSum(cur.right,nsum)) * getSum(tr,nsum);
            if (temp > max)
                max = temp;
            cur.right = tr;
            helper(root, cur.right,nsum);
        }

    }

    public long getSum(TreeNode root, HashMap<TreeNode, Long> nsum) {
        if (root == null)
            return 0;

        if (nsum.containsKey(root))
            return nsum.get(root);

        long ans = getSum(root.left, nsum) + getSum(root.right, nsum) + root.val;
        nsum.put(root, ans);
        return ans;
    }
}
