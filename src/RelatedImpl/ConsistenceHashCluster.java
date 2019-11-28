package RelatedImpl;/*
 * @author:
 * @date:  2019/11/26/026
 * @description:
 */


import java.util.*;

/*
    ConsistenceHash algorithm implements and test
    distributed file system
 */
public class ConsistenceHashCluster {

    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */

    public static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};

    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    private static List<String> realNodes = new LinkedList<>();

    private static int SubVirtualNodeNum = 5;

    // initial
    static {

        for (String server : servers) {
            realNodes.add(server);
            for (int i = 1; i <= SubVirtualNodeNum; i++) {
                StringBuilder sb = new StringBuilder(server);
                sb.append("##VN" + i);
                String target = sb.toString();
                int vhashkey = getHash(target);
                System.out.println("add virtual node :" + target);
                virtualNodes.put(vhashkey, target);
            }
        }
    }

    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static String getServer(String key) {

        int temp = getHash(key);
        int targetIdx = virtualNodes.tailMap(temp).firstKey();
        String targetServer = virtualNodes.get(targetIdx);
        System.out.println("found node is :" + targetServer);
        // cut realNode
        int k = targetServer.indexOf("##");
             return targetServer.substring(0, k);
    }

    public static void main(String[] args) {

        String model = "abcd";
        StringBuilder sb = new StringBuilder(model);
        for (int i = 0; i < 10; i++) {
            sb.append(i);
           // System.out.println("string to put :" + sb.toString());
            getServer(sb.toString());
        }
    }
}
