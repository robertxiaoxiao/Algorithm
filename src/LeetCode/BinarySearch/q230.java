package LeetCode.BinarySearch;/*
 * @author: Robert
 * @date:  2020/2/1/001
 * @description:
 */

import BasicDStructure.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class q230 {

    public int kthSmallest(TreeNode root, int k) {

        TreeNode temp = root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode top = null;
        while (k != 0) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            top = stack.pop();
            k--;
            root = top.right;
        }
        return top.val;
    }

    public boolean judge(TreeNode root, int k) {
        if (root == null)
            return k == 0;
        int rest = getsize(root.left) + 1 - k;
        if (rest >= 0)
            return true;
        else
            return false;
    }

    public int getsize(TreeNode root) {
        if (root == null)
            return 0;

        return getsize(root.left) + getsize(root.right) + 1;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;

        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

}
