package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/21/021
 * @description:
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class q589 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        //helper(res, root);
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node tnode = stack.pop();
            if (tnode == null)
                continue;
            res.add(tnode.val);
            List<Node> temp = tnode.children;
            for (int i = temp.size() - 1; i >= 0; i--) {
                Node n = temp.get(i);
                if (n != null)
                    stack.add(n);
            }
        }
        return res;
    }

    public List<Integer> preorder(Node root, List<Integer> res) {

        if (root == null)
            return res;
        res.add(root.val);

        for (int i = 0; i < root.children.size(); i++) {
            res = preorder(root.children.get(i), res);
        }
        return res;
    }

    public void helper(List<Integer> res, Node root) {
        if (root == null)
            return;
        res.add(root.val);
        for (Node next : root.children) {
            helper(res, next);
        }
    }
}
