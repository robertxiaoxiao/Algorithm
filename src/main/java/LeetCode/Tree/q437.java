package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/29/029
 * @description:
 */

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class q437 {

    static int cnt = 0;

    public int pathSumI(TreeNode root, int sum) {
        cnt = 0;
        cal(root, sum);
        return cnt;
    }

    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> res = new HashMap<>();
        // init
        res.put(0, 1);
        return helper(root, 0, sum, res);
    }

    public int helper(TreeNode root, int cur, int sum, HashMap<Integer, Integer> res) {
        if (root == null)
            return 0;
        cur += root.val;
        int ans = res.getOrDefault(cur - sum, 0);
        res.merge(cur, 1, Integer::sum);
        ans += helper(root.left, cur, sum, res);
        ans += helper(root.right, cur, sum, res);
        res.merge(cur, -1, Integer::sum);
        if (res.get(cur) == 0)
            res.remove(cur);

        return ans;
    }

    public void cal(TreeNode root, int sum) {
        if (root == null)
            return;
        dfs(root, sum, 0);
        cal(root.left, sum);
        cal(root.right, sum);
    }

    public void dfs(TreeNode root, int sum, int presum) {
        if (root == null)
            return;

        int cursum = presum + root.val;
        if (cursum == sum)
            cnt++;
        dfs(root.left, sum, cursum);
        dfs(root.right, sum, cursum);
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> savedSum = new HashMap();
        savedSum.put(0, 1);
        int sum = 0;
        int result = 0;
        for (int num : nums) {
            sum += num;
            // a[0,j] = sum
            // a[0,i] = sum-k
            // a(i,j] = k
            result += savedSum.getOrDefault(sum - k, 0);
            savedSum.merge(sum, 1, Integer::sum);
        }
        return result;
    }


}
