package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/13/013
 * @description:
 */

public class q450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val == key) {
            if (root.right != null) {
                TreeNode left = root.left;
                TreeNode ans = root.right;
                root = root.right;
                while (root.left != null)
                    root = root.left;
                root.left = left;
                return ans;
            } else {
                root = root.left;
                return root;
            }

        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            root.left = deleteNode(root.left, key);
            return root;
        }
    }

    public int findpostcessor(TreeNode root) {
        TreeNode par = root;
        TreeNode temp = root.right;
        boolean flag = true;
        while (temp.left != null) {
            par = temp;
            temp = temp.left;
            flag = false;
        }
        if (flag)
            par.right = null;
        else
            par.left = null;

        return temp.val;
    }

    public int findprecessor(TreeNode root) {
        TreeNode par = root;
        TreeNode temp = root.left;
        boolean flag = true;
        while (temp.right != null) {
            par = temp;
            temp = temp.right;
            flag = false;
        }
        if (flag)
            par.left = null;
        else
            par.right = null;
        return temp.val;
    }

    public TreeNode findNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (root.val == key)
            return root;
        else if (root.val < key)
            return findNode(root.right, key);
        else
            return findNode(root.left, key);
    }
}
