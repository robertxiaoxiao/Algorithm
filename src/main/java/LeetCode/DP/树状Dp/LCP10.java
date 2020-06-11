package LeetCode.DP.树状Dp;

import BasicDStructure.Tree.TreeNode;

public class LCP10 {

    // leetcode-cn spring code competition

    public double minimalExecTime(TreeNode root) {
        return helper(root, 2)[0];
    }

    public double[] helper(TreeNode root, int n) {
        if (root == null)
            return new double[]{0.0, 0.0};

        double[] lt = helper(root.left, n);
        double[] rt = helper(root.right, n);
        double sum = lt[1] + rt[1];

        double minTime = Math.max(lt[0], Math.max(rt[0], sum / n)) + (double) root.val;

        return new double[]{minTime, sum + (double) root.val};
    }

}
