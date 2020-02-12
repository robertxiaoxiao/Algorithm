package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/12/012
 * @description:
 */

import BasicDStructure.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class q98 {


    public boolean helper(TreeNode root, Integer lower, Integer upper) {

        if (root == null)
            return true;

        int val = root.val;
        if (lower != null && val <= lower)
            return false;

        if (upper != null && val >= upper)
            return false;

        if (!helper(root.right, val, upper))
            return false;

        if (!helper(root.left, lower, val))
            return false;

        return true;
    }

    public boolean isValidBST(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        for (int i = 0; i < ans.size() - 1; i++) {
            if (ans.get(i) >= ans.get(i + 1))
                return false;
        }
        return true;
    }

    public void inorder(TreeNode root, List<Integer> ans) {
        if (root == null)
            return;
        inorder(root.left, ans);
        ans.add(root.val);
        inorder(root.right, ans);
    }
}
