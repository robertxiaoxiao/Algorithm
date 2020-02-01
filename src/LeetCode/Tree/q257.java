package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q257 {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        dfs(root, res, "");
        return res;
    }

    public void dfs(TreeNode root, List<String> res, String pre) {
        if (root == null)
            return;

        String cur = pre + root.val + "->";
        if (root.left == null && root.right == null) {
            res.add(cur.substring(0, cur.length() - 1));
            return;
        }

        if (root.left != null)
            dfs(root.left, res, cur);

        if (root.right != null)
            dfs(root.right, res, cur);
    }

}
