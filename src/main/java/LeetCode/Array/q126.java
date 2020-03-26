package LeetCode.Array;/*
 * @author:
 * @date:  2019/10/23/023
 * @description:
 */

import javax.print.DocFlavor;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

public class q126 {

    public static void main(String[] args) {
        String beginword = "hit";
        String endword = "cog";
        String[] words =   {"hot","dot","dog","lot","log","cog"};

        List<String>  list=new LinkedList<String>();
        for(String s: words)
            list.add(s);
        q126 q=new q126();
        q.findLadders(beginword,endword,list);
//        createMap(wordlist,beginword);
//        computeShortLength(beginword,endword);
//        System.out.println("short length is :"+shortLen);

        //findLaddersTOL(beginword, endword, wordlist);

//        for (Map.Entry entry : dictMap.entrySet()) {
//            System.out.print(entry.getKey() + "   ");
//            for (String word : (List<String>) entry.getValue())
//                System.out.print(word + "   ");
//            System.out.println();
//        }
//
//        for (List<String> list : result) {
//            for (String str : list)
//                System.out.print(str + "   ");
//            System.out.println();
//        }
    }

    static HashMap<String, List<String>> dictMap = new HashMap<>();
    static int shortLen = Integer.MAX_VALUE;

    static List<String> seen = new LinkedList<>();
    static List<String> path = new LinkedList<>();
    static List<List<String>> result = new LinkedList<>();

    /*
      1   bfs to count the shortlen  of the map  and create the neighbers
      2   hashset dict   and  new the neighbors
      3   dfs the node in shortest length ;
     */

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new LinkedList<>();
        HashSet<String> dict = new HashSet<>(wordList);
        HashMap<String, Integer> distance = new HashMap<>();
        HashMap<String, ArrayList<String>> neighbors = new HashMap<>();
        List<String> solution = new LinkedList<>();
        dict.add(beginWord);
        bfs(beginWord, endWord, dict, distance, neighbors);
        for (String str : dict)
            System.out.print(str + "   ");
        System.out.println();

        dfs(beginWord, endWord, solution, neighbors, distance, result);

        System.out.println(result.size());

        for (List<String> list : result) {
            for (String str : list)
                System.out.print(str + "   ");
            System.out.println(result.size());
        }

        return result;
    }

    private void bfs(String beginWord, String endWord, HashSet<String> dict, HashMap<String, Integer> distance, HashMap<String, ArrayList<String>> neighbors) {
        for (String str : dict)
            neighbors.put(str, new ArrayList<>());
        System.out.println("begin");
        Queue<String> queue = new LinkedList<String>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        //distance  to mark the distance and whether visited
        // distance stores the inner to outer layer of nodes
        while (!queue.isEmpty()) {
            boolean found = false;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                int curdistance = distance.get(cur);
                ArrayList<String> nodeneighbors = getNeighbors(cur, dict);
                for (String neighbor : nodeneighbors) {
                    // to add visited node
                    neighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curdistance + 1);
                        if (endWord.equals(neighbor))
                            found = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }
            // the same level nodes may have the  different level to arrive ,so we must do the bfs search
            if (found)
                break;
        }

        for(Map.Entry entry : distance.entrySet())
            System.out.println(entry.getKey()+"  "+entry.getValue());
    }

    private ArrayList<String> getNeighbors(String cur, HashSet<String> dict) {
        ArrayList<String> result = new ArrayList<>();
        int len = cur.length();
        char[] chs = cur.toCharArray();
        //O(1) to find the specific dict ;
        //O(kl) to get the neighibors rather than O(N)
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < len; i++) {
                if (chs[i] == ch)
                    continue;
                char oldchar = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    result.add(String.valueOf(chs));
                }
                chs[i] = oldchar;
            }
        }
        return result;
    }

    private void dfs(String cur, String endword, List<String> list, HashMap<String, ArrayList<String>> neighbors, HashMap<String, Integer> distance, List<List<String>> res) {
       // System.out.println("added:"+cur);
        list.add(cur);
        if (endword.equals(cur)) {
     //       System.out.println("added");
            res.add(new ArrayList<>(list));
        } else {
            for (String neighbor : neighbors.get(cur)) {
                // make sure each step are in the shortest way if it can be arrived
                if (distance.get(neighbor) == distance.get(cur) + 1)
                    dfs(neighbor, endword, list, neighbors, distance, res);
            }
        }
        list.remove(list.size() - 1);
    }
//
//    public static List<List<String>> findLaddersTOL(String beginWord, String endWord, List<String> wordList) {
//
//        int flag = 0;
//        for (String word : wordList)
//            if (word.equalsIgnoreCase(endWord))
//                flag = 1;
//        if (flag == 0)
//            return result;
//        createMap(wordList, beginWord);
//        computeShortLength(beginWord, endWord);
//        System.out.println("short length is :" + shortLen);
//
//        int step = 0;
//        path.add(beginWord);
//        dfs(beginWord, endWord, seen, path, step, result);
//        return result;
//    }

//    private static void computeShortLength(String beginword, String endword) {
//        List<String> seen = new LinkedList<>();
//        HashMap<String, Integer> distance = new HashMap<>();
//        bfs(beginword, endword, seen, distance);
//        //  System.out.println("current shor len:"+distance.get(endword));
//    }

    // map.get() need to take care of the situation  where return value may be null
//    private static void dfs(String beginword, String endword, List<String> seen, List<String> path, int step, List<List<String>> result) {
//
//        if (step > shortLen)
//            return;
//
//        if (beginword.equalsIgnoreCase(endword)) {
//            System.out.println("current path: found" + beginword + "  " + endword + step);
//            result.add(new LinkedList<>(path));
//            return;
//        }
//
//        for (String next : dictMap.get(beginword)) {
//            if (!seen.contains(next)) {
//                //      System.out.println("current step  "+step);
//                path.add(next);
//                seen.add(next);
//                dfs(next, endword, seen, path, step + 1, result);
//                seen.remove(seen.size() - 1);
//                path.remove(path.size() - 1);
//            }
//        }
//    }

    // to optimize
//    private static void bfs(String beginword, String endword, List<String> seen, HashMap<String, Integer> distance) {
//
//        Queue<String> queue = new ArrayDeque<>();
//        queue.add(beginword);
//        distance.put(beginword, 0);
//
//        while (!queue.isEmpty()) {
//            String first = queue.poll();
//            int curdistance = distance.get(first);
//            for (String next : dictMap.get(first)) {
//                if (next != null && !distance.containsKey(next)) {
//                    distance.put(next, curdistance + 1);
//
//                    if (next.equalsIgnoreCase(endword)) {
//                        shortLen = distance.get(endword);
//                        return;
//                    }
//                    queue.add(next);
//                }
//            }
//        }
//    }


    private static void createMap(List<String> wordlist, String beginword) {
        if (wordlist.contains(beginword)) {
            dictMap.put(beginword, new LinkedList<>());
            for (String word : wordlist) {
                if (isEdge(word, beginword))
                    dictMap.get(beginword).add(word);
            }
        }
        // final wordlist
        int len = wordlist.size();
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++) {
                String wa = wordlist.get(i);
                String wb = wordlist.get(j);
                if (!dictMap.containsKey(wa))
                    dictMap.put(wa, new LinkedList<String>());

                if (!dictMap.containsKey(wb))
                    dictMap.put(wb, new LinkedList<String>());

                if (isEdge(wa, wb)) {
                    dictMap.get(wa).add(wb);
                    dictMap.get(wb).add(wa);
                }
            }
    }

    public static boolean isEdge(String wa, String wb) {
        int cnt = 0;
        for (int i = 0; i < wa.length(); i++) {
            if (wa.charAt(i) != wb.charAt(i))
                cnt++;

            if (cnt >= 2)
                return false;
        }
        return true;
    }


}
