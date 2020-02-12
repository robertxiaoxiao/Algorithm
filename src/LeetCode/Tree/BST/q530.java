package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/12/012
 * @description:
 */

import BasicDStructure.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

public class q530 {

    public int getMinimumDifference(TreeNode root) {
        int ans = Integer.MAX_VALUE;
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        if (res.size() <= 1)
            return 0;

        for (int i = 0; i < res.size() - 1; i++)
            ans = Math.min(ans, res.get(i + 1) - res.get(i));
        return ans;
    }

    static int min = Integer.MAX_VALUE;

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null)
            return;

        inorder(root.left, res);
        res.add(root.val);
        int size=res.size();
        if(res.size()>=2)
            min=Math.min(min,res.get(size-1)-res.get(size-2));
        inorder(root.right, res);
    }
}
