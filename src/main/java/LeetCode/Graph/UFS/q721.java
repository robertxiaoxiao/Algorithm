package LeetCode.Graph.UFS;/*
 * @author: Robert
 * @date:  2020/1/13/013
 * @description:
 */

import java.util.*;

public class q721 {

//    class ufs {
//
//        HashMap<Integer, String> names = new HashMap<>();
//        HashMap<Integer, HashSet<String>> res = new HashMap<>();
//
//        public void insert(List<String> list) {
//            String p = list.get(0);
//            int n = list.size();
//            boolean same = false;
//            int k = -1;
//            if (names.values().contains(p)) {
//                k = getNum(p);
//                HashSet<String> temp = res.get(k);
//                for (int i = 1; i < n; i++) {
//                    String t = list.get(i);
//                    if (temp.contains(t)) {
//                        same = true;
//                        break;
//                    }
//                }
//            }
//
//            if (same) {
//                for (int i = 1; i < n; i++)
//                    res.get(k).add(list.get(i));
//            } else {
//                k = names.size() + 1;
//                names.put(k, p);
//                HashSet hset = new HashSet<>();
//                for (int i = 1; i < n; i++)
//                    hset.add(list.get(i));
//                res.put(k, hset);
//            }
//        }
//
//        public int getNum(String p) {
//            for (int k : names.keySet())
//                if (names.get(k).equals(p))
//                    return k;
//
//            return -1;
//        }
//
//        public List<List<String>> accountsMerge() {
//            List<List<String>> reslist = new LinkedList<>();
//            for (int key : names.keySet()) {
//                List<String> temp = new LinkedList<>();
//                temp.addAll(res.get(key));
//                Collections.sort(temp);
//                temp.add(0,names.get(key));
//                reslist.add(temp);
//            }
//            return reslist;
//        }
//
//    }

    class ufs {
        HashMap<Integer, Integer> hset = new HashMap<>();

        public int findParent(int i) {
            if (!hset.containsKey(i))
                hset.put(i, i);
            int pj = hset.get(i);
            if (pj != i) {
                int key = findParent(pj);
                hset.put(i, key);
                //NOTICE : USING HASHMAP TO IMPLEMENT THE UNIONFINDSET ,IF MISSING THIS ASSIGNMENT ,IT WILL BE WRONG
                pj = key;
            }
            return pj;
        }

        public void union(int i, int j) {
            int pi = findParent(i);
            int pj = findParent(j);
            hset.put(pi, pj);
        }
    }

//    class ufs{
//        int[] parent;
//        public ufs() {
//            parent = new int[10001];
//            for (int i = 0; i <= 10000; ++i)
//                parent[i] = i;
//        }
//        public int find(int x) {
//            if (parent[x] != x) parent[x] = find(parent[x]);
//            return parent[x];
//        }
//        public void union(int x, int y) {
//            parent[find(x)] = find(y);
//        }
//    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        ufs ufs = new ufs();
        List<List<String>> ans = new LinkedList<>();
        HashMap<String, String> emailtoname = new HashMap<>();
        HashMap<String, Integer> emailtoId = new HashMap<>();
        int idx = 0;
        for (List<String> list : accounts) {
            String name = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String curmail = list.get(i);
                if (!emailtoId.containsKey(curmail))
                    emailtoId.put(curmail, idx++);
                int cid = emailtoId.get(curmail);
                emailtoname.put(curmail, name);
                ufs.union(emailtoId.get(list.get(1)), cid);
            }
        }
        HashMap<Integer, List<String>> hm = new HashMap<>();
        for (String mail : emailtoname.keySet()) {
            int idex = ufs.findParent(emailtoId.get(mail));
            if (!hm.containsKey(idex))
                hm.put(idex, new LinkedList<>());
            hm.get(idex).add(mail);
        }
        for (List<String> component : hm.values()) {
            Collections.sort(component);
            component.add(0, emailtoname.get(component.get(0)));
        }

        return new ArrayList<>(hm.values());

    }


}
