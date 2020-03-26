package leetcodeTest.Week8;/*
 * @author: Robert
 * @date:  2019/12/29/029
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q2 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        List<Integer> res1 = new LinkedList<>();
        List<Integer> res2 = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        helper(root1, res1);
        helper(root2, res2);
        int i = 0;
        int j = 0;
        while (i < res1.size() || j < res2.size()) {
            while (i < res1.size() && j < res2.size()) {
                if (res1.get(i) < res1.get(j))
                    res.add(res1.get(i++));
                else
                    res.add(res2.get(j++));
            }
            while (i < res1.size())
                res.add(res1.get(i++));

            while (j < res2.size())
                res.add(res2.get(j++));
        }
        return res;
    }

    public void helper(TreeNode root1, List<Integer> res) {
        if (root1 == null)
            return;
        if (root1.left != null)
            helper(root1.left, res);
        res.add(root1.val);
        if (root1.right != null)
            helper(root1.right, res);
    }
}