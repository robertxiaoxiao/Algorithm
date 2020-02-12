package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/12/012
 * @description:
 */

public class q701 {


    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode ans = root;
        insert(root, null,val);
        return ans;
    }

    public void insert(TreeNode root, TreeNode par, int val) {
        if (root == null) {
            if (par.val < val)
                par.right = new TreeNode(val);
            else
                par.left = new TreeNode(val);
            return;
        }

        if (root.val < val)
            insert(root.right, root, val);
        else
            insert(root.left, root, val);
    }

}
