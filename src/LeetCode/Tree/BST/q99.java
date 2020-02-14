package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/13/013
 * @description:
 */

import org.apache.catalina.LifecycleState;

import java.util.LinkedList;
import java.util.List;

public class q99 {
    TreeNode pre;
    TreeNode preNode;
    TreeNode f;
    TreeNode s;

    public void recoverTree(TreeNode root) {
        pre = null;
        f = null;
        s = null;
        inorder(root);
        if (f != null) {
            int t = f.val;
            f.val = s.val;
            s.val = t;
        }
    }

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

    private void visit(TreeNode cur) {
        if (preNode != null && preNode.val > cur.val) {
            if (f == null)
                f = preNode;
            s = cur;
        }
        preNode = cur;
    }

    public void recover(TreeNode root) {
        if (root == null)
            return;

        recover(root.left);
        if (pre != null && pre.val > root.val) {
            if (f == null)
                f = pre;
            s = root;
        }
        pre = root;
        recover(root.right);
    }


}
