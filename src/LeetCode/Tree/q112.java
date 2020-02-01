package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

public class q112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        if (root.left == null && root.right == null && sum == root.val)
            return true;

        boolean flag = false;
        if (root.left != null)
            flag |= hasPathSum(root.left, sum - root.val);
        if (root.right != null)
            flag |= hasPathSum(root.right, sum - root.val);

        return flag;
    }
}
