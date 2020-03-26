package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/8/008
 * @description:
 */

public class q538 {

     int adder;
    public TreeNode convertBST(TreeNode root){
        adder=0;
        help(root);
        return root;
    }
    public void help(TreeNode t){
        if(t==null)
            return;
        help(t.right);
        t.val+=adder;
        adder=t.val;
        help(t.left);
    }

     public TreeNode convertBSTError(TreeNode root) {
        if (root == null)
            return null;
        if (root.right != null) {
            TreeNode tr = convertBSTError(root.right);
            root.val = root.val + tr.val;
        }
        if (root.left != null) {
            TreeNode nleft = convertBSTError(root.left);
            root.left.val = nleft.val + root.val;
        }
        return root;
    }


}
