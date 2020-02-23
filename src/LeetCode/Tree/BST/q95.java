package LeetCode.Tree.BST;/*
 * @author: Robert
 * @date:  2020/2/21/021
 * @description:
 */

import DynamaicP.DPAlo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class q95 {

    public List<TreeNode> generateTrees(int n) {

        if (n == 0)
            return new LinkedList<>();

        List<TreeNode>[][] dp = new List[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = new LinkedList<>();
            dp[i][i].add(new TreeNode(i));
        }

        List<TreeNode> nullsub = new LinkedList<>();
        nullsub.add(null);
        for (int len = 2; len <= n; len++)
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = new LinkedList<>();
                for (int k = i; k <= j; k++) {
                    List<TreeNode> left = (i == k ? nullsub : dp[i][k - 1]);
                    List<TreeNode> right = (j == k ? nullsub : dp[k + 1][j]);
                    for (TreeNode l : left)
                        for (TreeNode r : right) {
                            TreeNode t = new TreeNode(k);
                            t.left = l;
                            t.right = r;
                            dp[i][j].add(t);
                        }
                }
            }

        return dp[1][n];
    }

}
