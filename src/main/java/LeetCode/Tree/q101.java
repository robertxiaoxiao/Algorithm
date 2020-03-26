package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

import java.util.LinkedList;
import java.util.Queue;

public class q101 {
    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }

    public boolean isSymmetricUsingiter(TreeNode root) {
        Queue<TreeNode> ql = new LinkedList<>();
        Queue<TreeNode> qr = new LinkedList<>();
        if (root == null)
            return true;
        ql.add(root.left);
        qr.add(root.right);
        while (!ql.isEmpty() && !qr.isEmpty()) {
            if (ql.isEmpty() & qr.isEmpty())
                return false;
            TreeNode t1 = ql.poll();
            TreeNode t2 = qr.poll();
            if (!check(t1, t2))
                return false;
            if (t1 != null && t2 != null) {
                ql.add(t1.left);
                qr.add(t2.right);
                ql.add(t2.left);
                qr.add(t1.right);
            }
        }

        return true;
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;
        return true;
    }

    public boolean helper(TreeNode root, TreeNode mroot) {
        if (root == null && mroot == null)
            return true;
        else if (root == null || mroot == null)
            return false;
        else {
            if (root.val != mroot.val)
                return false;

            return helper(root.left, mroot.right) && helper(root.right, mroot.left);
        }
    }

}
