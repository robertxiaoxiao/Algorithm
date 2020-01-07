package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/7/007
 * @description:
 */

import java.util.List;

public class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
}
