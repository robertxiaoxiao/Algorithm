package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/12/012
 * @description:
 */

public class q700 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        else if (root.val < val)
            return searchBST(root.right, val);
        else
            return searchBST(root.left, val);
    }
}
