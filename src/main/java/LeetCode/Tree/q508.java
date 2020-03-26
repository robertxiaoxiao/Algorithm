package LeetCode.Tree;/*
 * @author: Robert
 * @date:  2020/1/31/031
 * @description:
 */

import java.util.HashMap;

public class q508 {
    static int cnt = -1;
    static int sum = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        cnt = -1;
        HashMap<Integer, Integer> record = new HashMap<>();
        helper(root, record);
        int max = -1;
        int[] res = new int[sum];
        int idx = 0;
        for (int key : record.keySet())
            if (record.get(key) == cnt)
                res[idx++] = key;
        return res;
    }

    public int helper(TreeNode root, HashMap<Integer, Integer> record) {
        if (root == null)
            return 0;

        int l = helper(root.left, record);
        int r = helper(root.right, record);
        int sum = l + r + root.val;
        record.merge(sum, 1, Integer::sum);

        int k = record.get(sum);
        if (k == cnt)
            sum++;
        if (k > cnt) {
            cnt = k;
            sum = 1;
        }

        return sum;
    }


}
