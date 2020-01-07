package leetcodeTest.Week9;/*
 * @author: Robert
 * @date:  2020/1/5/005
 * @description:
 */

import java.util.*;

public class q3 {

    static class node {
        String vedio;
        int times;

        public node(String vedio, int times) {
            this.vedio = vedio;
            this.times = times;
        }
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        HashMap<Integer, List<Integer>> fmap = buildfriends(friends);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        int steps = 0;
        HashSet<Integer> seen = new HashSet<>();
        seen.add(id);
        while (steps != level) {
            int size = queue.size();
            while (size > 0) {
                int cur = queue.poll();
                for (int friend : fmap.get(cur)) {
                    if (!seen.contains(friend)) {
                        queue.add(friend);
                        seen.add(friend);
                    }
                }
            }
            steps++;
        }
        PriorityQueue<node> pq = new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                if (o1.times > o2.times)
                    return 1;
                else if (o1.times < o2.times)
                    return -1;
                else {
                    return o1.vedio.compareTo(o2.vedio);
                }
            }
        });

        HashMap<String ,Integer>  h=new HashMap<>();
        for (int idx : queue) {
            for(String vidio :watchedVideos.get(idx))
               h.put(vidio,h.getOrDefault(vidio,0)+1);
        }

        for(String key:h.keySet())
        {
            pq.add(new node(key,h.get(key)));
        }

        List<String>   res=new LinkedList<>();
        while(!pq.isEmpty())
        {
            res.add(pq.poll().vedio);
        }
        return  res;
    }

    public HashMap<Integer, List<Integer>> buildfriends(int[][] friends) {

        HashMap<Integer, List<Integer>> fmap = new HashMap<>();

        for (int idx = 0; idx < friends.length; idx++) {
            List<Integer> cur = new LinkedList<Integer>();
            for (int curf : friends[idx])
                cur.add(curf);
            fmap.put(idx, cur);
        }
        return fmap;
    }
}
