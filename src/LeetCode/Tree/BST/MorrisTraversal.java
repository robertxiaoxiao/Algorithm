package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/13/013
 * @description:
 */

public class MorrisTraversal {


    public void inorder(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                visit(cur);
                cur = cur.right;
            } else {
                //  find predecessor in inorder traversal
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;

                // threaded binary tree has not built
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    visit(cur);
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
    }



    public void preorder(TreeNode root) {

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                visit(cur);
                cur = cur.right;
            } else {
                //  find predecessor in inorder traversal
                TreeNode pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;

                // threaded binary tree has not built
                if (pre.right == null) {
                    // the key value
                    visit(cur);
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
    }







    public void visit(TreeNode cur) {
    }

}
