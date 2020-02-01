package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class q113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        if (root == null)
            return ans;
        temp.add(root.val);
        dfs(ans, temp, root, root.val, sum);
        return ans;
    }

    private void pathSum(TreeNode root, int sum, List<List<Integer>> list, ArrayList<Integer> path) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            List<Integer> current = new ArrayList<>(path);
            list.add(current);
        }
        pathSum(root.left, sum - root.val, list, path);
        pathSum(root.right, sum - root.val, list, path);
        path.remove(path.size() - 1);
    }

    public void dfs(List<List<Integer>> ans, List<Integer> temp, TreeNode curnode, int cursum, int sum) {

        if (curnode == null)
            return;

        if (cursum == sum && curnode.left == null && curnode.right == null) {
            ans.add(new LinkedList<>(temp));
            return;
        }
        if (curnode.left != null) {
            temp.add(curnode.left.val);
            dfs(ans, new LinkedList<>(temp), curnode.left, cursum + curnode.right.val, sum);
            temp.remove(temp.size() - 1);
        }

        if (curnode.right != null) {
            temp.add(curnode.right.val);
            dfs(ans, temp, curnode.right, cursum + curnode.right.val, sum);
            temp.remove(temp.size() - 1);
        }
    }

}
