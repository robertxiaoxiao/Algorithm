package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/30/030
 * @description:
 */

public class q124 {

    static int ans;

    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        helper(root);
        return ans;
    }

    // helper : sum of max path sum
    // a: root must be used
    // at most one child should be included
    public int helper(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;

        int l = Math.max(0, helper(root.left));
        int r = Math.max(0, helper(root.right));
        int temp = l + r + root.val;
        if (ans < temp)
            ans = temp;
        return Math.max(l, r) + root.val;
    }

}
