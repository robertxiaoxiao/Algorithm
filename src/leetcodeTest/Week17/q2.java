package leetcodeTest.Week17;

import java.util.Arrays;
import java.util.Comparator;

public class q2 {

    class Node {
        char ch;
        int[] ranks;

        public Node(char ch, int nsize) {
            this.ch = ch;
            this.ranks = new int[nsize];
        }
    }

    public String rankTeams(String[] votes) {
        int n = votes.length;
        if (n == 0)
            return "";
        if (n == 1)
            return votes[0];

        int nsize = votes[0].length();
        Node[] nodes = new Node[26];
        for (int i=0;i<26;i++)
            nodes[i]=new Node((char)('A'+i),26);

        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                char ch = vote.charAt(i);
                nodes[ch - 'A'].ranks[i]++;
            }
        }

        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                for (int i = 0; i < node.ranks.length; i++) {
                    int t = node.ranks[i] - t1.ranks[i];
                    if (t == 0)
                        continue;
                    return -t;
                }
                return node.ch - t1.ch;
            }
        });

        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Node tn : nodes) {
            sb.append(tn.ch);
            i++;
            if (i == nsize)
                break;
        }
        return sb.toString();
    }
}
