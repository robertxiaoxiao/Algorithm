package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

import java.util.LinkedList;
import java.util.List;

public class q590 {
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new LinkedList<>();
        postorder(root, ans);
        return ans;
    }

    public void postorder(Node root, List<Integer> ans) {
        if (root == null)
            return;

        for (Node node : root.children) {
            postorder(node, ans);
        }

        ans.add(root.val);
    }
}
