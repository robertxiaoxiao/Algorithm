package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

public class q297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String ans = helper(root);
        System.out.println(ans);
        return ans;
    }

    public String helper(TreeNode root) {

        if (root == null)
            return "null";

        String l = serialize(root.left);
        String r = serialize(root.right);

        return root.val + "," + l + "," + r;
    }

    class state {
        int cur;

        public state(int cur) {
            this.cur = cur;
        }

        public void add() {
            cur++;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String temp = data.substring(1, data.length() - 1);
        state s = new state(0);
        String[] datas = data.split(",");
        return deserialize(datas, s);
    }

    public TreeNode deserialize(String[] datas, state cs) {
        int cur = cs.cur;
        if (datas[cur].equals("null"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(datas[cur]));
        cs.add();
        root.left = deserialize(datas, cs);
        cs.add();
        root.right = deserialize(datas, cs);
        return root;
    }
}
