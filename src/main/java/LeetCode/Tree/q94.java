package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/21/021
 * @description:
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class q94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inorder(root, res);
        return res;
    }

    public List < Integer > inorderTraversalIter(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            //   the left   node the right left
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;

        if (root.left != null)
            inorder(root.left, res);

        res.add(root.val);
        if (root.right != null)
            inorder(root.right, res);
    }

}
