package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

public class q814 {

    public TreeNode pruneTree(TreeNode root) {
        return helper(root);
    }

    public TreeNode helper(TreeNode root) {
        if (root == null)
            return null;

        if (root.val == 0 && root.left == null && root.right == null)
            return null;

        if (getWeight(root.left) == 0)
            root.left = null;
        else
            root.left = helper(root.left);

        if (getWeight(root.right) == 0)
            root.right = null;
        else
            root.right = helper(root.right);

        return root;
    }

    public int getWeight(TreeNode root) {
        if (root == null)
            return 0;
        int ans = root.val;
        if (root.left != null)
            ans += getWeight(root.left);

        if (root.right != null)
            ans += getWeight(root.right);
        return ans;
    }


    public TreeNode pruneTreeSimplify(TreeNode root) {
        if (isValidSubTree(root)) {
            root.left = pruneTreeSimplify(root.left);
            root.right = pruneTreeSimplify(root.right);
            return root;
        } else {
            // cut the tree
            return null;
        }
    }
    public boolean isValidSubTree(TreeNode node) {
        if (node == null) return false;
        if (node.val == 1) return true;

        return isValidSubTree(node.left) || isValidSubTree(node.right);
    }

}
