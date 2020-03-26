package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

import java.util.LinkedList;
import java.util.Queue;

public class q449 {


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "#";
        String l = serialize(root.left);
        String r = serialize(root.right);
        return root.val + "," + l + "," + r;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.split(",");
        Queue<String> queue = new LinkedList<String>();
        for (String s : datas)
            queue.add(s);
        return helper(queue);
    }

    public TreeNode helper(Queue<String> queue) {
        String cur = queue.poll();
        if (cur.equals("#"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = helper(queue);
        root.right = helper(queue);
        return root;
    }
}
