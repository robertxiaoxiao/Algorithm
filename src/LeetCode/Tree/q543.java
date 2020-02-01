package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

public class q543 {
    static int cnt;

    public int diameterOfBinaryTree(TreeNode root) {
        cnt = 0;
        helper(root);
        return cnt == 0 ? 0 : cnt - 1;
    }

    public int helper(TreeNode root) {
        if (root == null)
            return 0;

        int l = helper(root.left);
        int r = helper(root.right);

        int ans = l + r + 1;
        if (ans > cnt)
            cnt = ans;

        return Math.max(l, r) + 1;
    }
}
