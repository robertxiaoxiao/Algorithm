package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

public class q572 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return check(s, t);
    }

    public boolean check(TreeNode s, TreeNode t) {
        if (s == null)
            return false;

        if (s.val == t.val && check(s, t))
            return true;

        return check(s.left, t) || check(s.right, t);
    }

    public boolean helper(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null) {
            return false;
        } else {
            if (p.val != q.val)
                return false;
            return helper(p.left, q.left) && helper(p.right, q.right);
        }
    }

}
