package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q872 {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> ans1 = new LinkedList<>();
        List<Integer> ans2 = new LinkedList<>();
        getLeafs(root1, ans1);
        getLeafs(root2, ans2);
        int n = ans1.size();
        if (ans2.size() != n)
            return false;
        for (int i = 0; i < n; i++)
            if (ans1.get(i) != ans2.get(i))
                return false;
        return true;
    }

    public void getLeafs(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        getLeafs(root.left, res);
        getLeafs(root.right, res);

    }

}
