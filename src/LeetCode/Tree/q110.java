package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

public class q110 {

    public boolean isBalanced(TreeNode root) {
        return helper(root);
    }

    public boolean helper(TreeNode root) {
        if (root == null)
            return true;

        int l = getHeight(root.left, 0);
        int r = getHeight(root.right, 0);
        if (l - r > 1 || l - r < -1)
            return false;

        return helper(root.left) && helper(root.right);
    }

    public int getHeight(TreeNode root, int cur) {
        if (root == null)
            return cur;

        return Math.max(getHeight(root.left, cur), getHeight(root.right, cur)) + 1;
    }
}
