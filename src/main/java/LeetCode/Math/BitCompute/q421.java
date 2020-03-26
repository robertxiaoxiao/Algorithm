package LeetCode.Math.BitCompute;

public class q421 {

    class node {
        node[] subnodes = new node[2];
    }

    /*
       trie + greedy
     */
    public int findMaximumXOR(int[] nums) {
        node root = new node();
        for (int num : nums) {
            node p = root;
            for (int i = 31; i >= 0; i--) {
                int cur = (num >> i) & 1;
                if (p.subnodes[cur] == null)
                    p.subnodes[cur] = new node();
                p = p.subnodes[cur];
            }
        }
        int max = 0;
        for (int num : nums) {
            node p = root;
            int tnum = 0;
            for (int i = 31; i >= 0; i--) {
                int cur = (num >> i) & 1;
                if (p.subnodes[1 - cur] != null) {
                    p = p.subnodes[1 - cur];
                    tnum += (1 - cur) << i;
                } else {
                    p = p.subnodes[cur];
                    tnum += cur << i;
                }
            }
            max = Math.max(max, num ^ tnum);
        }
        return max;
    }

}
