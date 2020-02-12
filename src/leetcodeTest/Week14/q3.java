package leetcodeTest.Week14;/*
 * @author: Robert
 * @date:  2020/2/9/009
 * @description:
 */

import java.util.*;

public class q3 {

    class TweetCounts {
        HashMap<String, List<Integer>> record;

        public TweetCounts() {
            record = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            if (!record.containsKey(tweetName))
                record.put(tweetName, new ArrayList<>());
            List<Integer> temp = record.get(tweetName);
            temp.add(time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {

            TreeSet tset=new TreeSet();
            int times = 1;
            if (freq.equals("minnute")) {
                times = 60;
            } else if (freq.equals("hour"))
                times = 3600;
            else if (freq.equals("day"))
                times = 3600 * 12;
            List<Integer> ans = new ArrayList<>();
            int st = startTime * times;
            int et = (endTime + 1) * times;
            int interval = times;
            List<Integer> candidates = record.get(tweetName);
            HashMap<Integer, Integer> res = new HashMap<>();

            if (candidates == null)
                return ans;
            Collections.sort(candidates);
            for (Integer cur : candidates) {
                if (cur >= et || cur < st)
                    continue;
                int k = (cur / interval);
                if(k!=0)
                k = (cur%interval==0 )? k - 1:k;
                if (k == ans.size())
                    ans.add(1);
                ans.set(k, ans.get(k) + 1);
            }
            return ans;
        }
    }


}
