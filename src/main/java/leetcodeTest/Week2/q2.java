package leetcodeTest.Week2;/*
 * @author:
 * @date:  2019/11/17/017
 * @description:
 */

public class q2 {

    TreeNode root;
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public void FindElements(TreeNode newroot) {
        root = newroot;
        root.val = 0;
        recover(root);
    }

    public void recover(TreeNode root) {
        if (root == null)
            return;

        if (root.left != null) {
            root.left.val = root.val * 2 + 1;
            recover(root.left);
        }

        if (root.right != null) {
            root.right.val = root.val * 2 + 2;
            recover(root.right);
        }
    }

    public boolean find(int target) {

        return find(target, root);
    }

    public boolean find(int target, TreeNode root) {
        if (root == null)
            return false;
        if (root.val == target)
            return true;

        return find(target, root.left) | find(target, root.right);
    }

}
