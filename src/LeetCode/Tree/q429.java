package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q429 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new LinkedList<>();
        levelOrder(root, ans, 0);
        return ans;
    }

    public void levelOrder(Node root, List<List<Integer>> ans, int curlevel) {
        if (root == null)
            return;

        if (curlevel >= ans.size()) {
            ans.add(new LinkedList<>());
        }

        ans.get(curlevel).add(root.val);
        for (Node node : root.children)
            levelOrder(node, ans, curlevel + 1);
    }

}
