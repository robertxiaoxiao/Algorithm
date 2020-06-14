package leetcodeTest;

import java.util.*;

public class Week32 {


    public int[] runningSum(int[] nums) {
        int n=nums.length;
        int[] res=new int[n];
        res[0]=nums[0];
        for (int i=1;i<n;i++)
            res[i]=res[i-1]+nums[i];

        return res;
    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        PriorityQueue<Integer> pq=new PriorityQueue<>();
        HashMap<Integer,Integer> hm=new HashMap<>();
        int idx=0;
        for (int i :arr){
            if (!hm.containsKey(i))
                idx++;
            hm.put(i,hm.getOrDefault(i,0)+1);
        }

        for (int i:hm.keySet())
            pq.add(hm.get(i));

        if (k==0)
            return idx;
        while (k>=0&&!pq.isEmpty()){
            k-=pq.poll();
            idx--;
            if (k==0)
                return idx;
        }
        return idx+1;
    }


    public int minDays(int[] bloomDay, int m, int k) {
            int left=1;
            int right=(int)1e9+1;
            while (left<right){
                int mid = (left+right)/2;
                if (canFinish(mid,bloomDay,m,k))
                    right=mid;
                else
                    left=mid+1;
            }
            if (left>=(int)1e9+1)
                return -1;
            return left;
    }

    public boolean canFinish(int day,int[] bloomDay, int m, int k){
        int n=bloomDay.length;
        for (int i=0;i<n;i++){
            int val=bloomDay[i];
            int cur=0;
            while (val<=day){
                cur++;
                i++;
                if (cur==k) {
                    m--;
                    cur=0;
                }
                if (i>=n)
                    break;
                val=bloomDay[i];

            }
        }
        return m<=0;
    }


    class TreeAncestor {
        private int[][] parent = new int[100000 + 1][17];

        public TreeAncestor(int n, int[] parent) {
            for (int i = 0; i < n; i++) {
                this.parent[i + 1][0] = parent[i] + 1;
            }
            for (int j = 1; j < 17; j++) {
                for (int i = 1; i < this.parent.length; i++) {
                    this.parent[i][j] = this.parent[this.parent[i][j - 1]][j - 1];
                }
            }
        }


        public int getKthAncestor(int node, int k) {
            node = node + 1;
            for (int j = 16; j >= 0; j--) {
                if ((k & (1 << j)) != 0) {
                    node = parent[node][j];
                    if (node == 0)
                        break;
                }
            }
            return node - 1;
        }
    }



}
