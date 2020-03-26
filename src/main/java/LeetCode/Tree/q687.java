package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

public class q687 {

    static int cnt = 0;

    public int longestUnivaluePath(TreeNode root) {
        cnt = 0;
        helper(root);
        return cnt;
    }
    // helper : root must be included and at most one child should be included

    public int helper(TreeNode root) {
        if (root == null)
            return 0;

        int l = helper(root.left);
        int r = helper(root.right);
        int ans = 1;
        int tl = 0;
        int tr = 0;
        if (root.left != null && root.val == root.left.val) {
            ans += l;
            tl = l;
        }
        if (root.right != null && root.val == root.right.val) {
            ans += r;
            tr = r;
        }

        if (ans > cnt)
            cnt = ans;
        return Math.max(tl, tr) + 1;
    }
}
