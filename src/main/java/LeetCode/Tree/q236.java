package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

import java.util.LinkedList;
import java.util.Queue;

public class q236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;

        if (root.val == p.val || root.val == q.val)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            return root;

        return left == null ? right : left;
    }


}
