package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

public class q129 {
    static int ans = 0;

    public int sumNumbers(TreeNode root) {
        ans = 0;
        dfs(root, 0);
        return ans;
    }

    public void dfs(TreeNode root, int pre) {
        if (root == null)
            return;

        int cur = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            ans += cur;
            return;
        }
        if (root.left != null)
            dfs(root.left, cur);

        if (root.right != null)
            dfs(root.right, cur);
    }

}
