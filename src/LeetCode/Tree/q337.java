package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/2/7/007
 * @description:
 */

import java.util.HashMap;

public class q337 {

    public int robIII(TreeNode root) {
        return helper(root, false);
    }

    public int helper(TreeNode root, boolean robbing) {
        if (root == null)
            return 0;

        int lm = helper(root.left, false);
        int rm = helper(root.right, false);
        int v1 = lm + rm;
        if (robbing) {
            return v1;
        } else {
            int v2 = root.val + helper(root.left, true) + helper(root.right, true);
            return Math.max(v1, v2);
        }
    }

    public HashMap<TreeNode, Integer> robs;
    public HashMap<TreeNode, Integer> norobs;

    // dp[k,0]= max(dp[k.left,0],dp[k.left,1])+ max(dp[k.right,0],dp[k.right,1]);
    // dp[k,1]=k.val+dp[k.left,0]+dp[k.right,0]
    public int rob(TreeNode root) {
        robs = new HashMap<>();
        norobs = new HashMap<>();

        return Math.max(dfs(root, false), dfs(root, true));
    }

    public int dfs(TreeNode root, boolean robbed) {
        if (root == null)
            return 0;

        if (robbed && robs.containsKey(root)) {
            return robs.get(root);
        }

        if (!robbed && norobs.containsKey(root)) {
            return norobs.get(root);
        }

        if (robbed) {
            int ans = root.val + dfs(root.left, false) + dfs(root.right, false);
            robs.put(root, ans);
            return ans;
        } else {
            int l = Math.max(dfs(root.left, false), dfs(root.left, true));
            int r = Math.max(dfs(root.right, false), dfs(root.right, true));
            norobs.put(root, l + r);
            return l + r;
        }
    }

    public int robSimplify(TreeNode root) {
        int[] res=helper(root);
        return Math.max(res[0], res[1]);
    }

    public int[] helper(TreeNode root) {
        // res[0]: robbed
        // res[1]: norob
        int[] res = new int[2];

        if (root != null) {
            int[] l = helper(root.left);
            int[] r = helper(root.right);
            res[0] = root.val + l[1] + r[1];
            res[1] = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        }
        return res;
    }


}
