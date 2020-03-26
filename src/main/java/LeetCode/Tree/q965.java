package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

public class q965 {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null)
            return true;
        return helper(root, root.val);
    }

    public boolean helper(TreeNode root, int val) {
        if (root == null)
            return true;
        if (root.val != val)
            return false;

        return helper(root.left, val) && helper(root.right, val);
    }

}
