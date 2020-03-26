package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

public class q100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        return helper(p, q);
    }

    public boolean isSameTreeUsingtrick(TreeNode p, TreeNode q) {
        String tree1 = dfs("", p);
        String tree2 = dfs("", q);

        return tree1.equals(tree2);
    }

    public String dfs(String record, TreeNode root) {
        if(root == null) {
            return "n";
        }

        if(root.left == null && root.right == null) {
            return record + root.val;
        }

        record = record + root.val + dfs(record, root.left) + dfs(record, root.right);
        return record;
    }

    public boolean helper(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        else if (p == null || q == null) {
            return false;
        } else {
            if (p.val != q.val)
                return false;
            return helper(p.left, q.left) && helper(p.right, q.right);
        }
    }
}
