package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/21/021
 * @description:
 */

import java.util.List;

public class Node {
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
