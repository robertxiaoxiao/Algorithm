package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

public class q669 {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)
            return null;
        int cv = root.val;
        if (cv >= L && cv <= R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);

        } else if (cv < L) {
            root = trimBST(root.right, L, R);
        } else if (cv > R) {
            root = trimBST(root.left, L, R);
        }
        
        return root;
    }

}
