package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/2/7/007
 * @description:
 */

public class q979 {


    static int ans = 0;

    public int distributeCoins(TreeNode root) {
        ans = 0;
        helper(root);
        return ans;
    }

    private int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int ld = helper(node.left);
        int rd = helper(node.right);
        ans += Math.abs(ld) + Math.abs(rd);
        return ld + rd + node.val - 1;
    }

}
