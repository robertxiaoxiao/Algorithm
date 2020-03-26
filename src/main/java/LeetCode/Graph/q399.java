package LeetCode.Graph;/*
 * @author: Robert
 * @date:  2020/1/9/009
 * @description:
 */

import javax.xml.stream.events.EntityDeclaration;
import java.util.*;

public class q399 {


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, List<edge>> graph = new HashMap<>();
        int n = values.length;
        for (int i = 0; i < n; i++) {
            String c1 = equations.get(i).get(0);
            String c2 = equations.get(i).get(1);
            double k = values[i];
            putvalue(graph, c1, c2, k);
            putvalue(graph, c2, c1, 1 / k);
        }

        int m = queries.size();
        double[] ans = new double[m];
        int idx = 0;

        for (int i = 0; i < m; i++) {
            String c1 = queries.get(i).get(0);
            String c2 = queries.get(i).get(1);
            if (!graph.containsKey(c1) || !graph.containsKey(c2)) {
                //ans[idx++] = c1.equals(c2)?1.0:-1.0;
                ans[idx++] = -1.0;
            } else {
                HashSet<String> visiting = new HashSet<>();
                ans[idx++] = dfs(graph, c1, c2, visiting);
            }
        }
        return ans;
    }

    public void putvalue(HashMap<String, List<edge>> graph, String s, String end, double weight) {
        if (!graph.containsKey(s))
            graph.put(s, new ArrayList<>());
        List<edge> edges = graph.get(s);
        edge e = new edge(end, weight);
        edges.add(e);
    }

    static class edge {
        public String node;
        public double weight;

        public edge(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public double dfs(HashMap<String, List<edge>> graph, String s, String e, HashSet<String> visited) {
        if (s.equals(e))
            return 1.0;

        if (visited.contains(s))
            return -1;
        visited.add(s);
        double ans = 1.0;
        for (edge adj : graph.get(s)) {
            String nnode = adj.node;
            if (visited.contains(nnode))
                continue;

            double k = adj.weight;
            double d = dfs(graph, nnode, e, visited);
            if (d > 0)
                return k * d;
        }

        return -1.0;
    }

    public double[] calcEquationUsingUF(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, edge> parent = new HashMap<>();
        int n = values.length;
        for (int i = 0; i < n; i++) {
            String c1 = equations.get(i).get(0);
            String c2 = equations.get(i).get(1);
            double k = values[i];
            if (!parent.containsKey(c1) && !parent.containsKey(c2)) {
                // c1/c2 =k    p: c1 ,{c2,k}
                edge p1 = new edge(c2, k);
                parent.put(c1, p1);
                edge p2 = new edge(c2, 1.0);
                parent.put(c2, p2);
            } else if (!parent.containsKey(c1)) {
                edge p1 = new edge(c2, k);
                parent.put(c1, p1);
            } else if (!parent.containsKey(c2)) {
                edge p2 = new edge(c1, 1.0 / k);
                parent.put(c2, p2);
            } else {
                edge p1 = findParent(c1, parent);
                edge p2 = findParent(c2, parent);
                if (!p1.node.equals(p2.node)) {
                    p1.node = p2.node;
                    p1.weight /= k * p2.weight;
                }
            }
        }
        int m = queries.size();
        double[] ans = new double[m];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            String c1 = queries.get(i).get(0);
            String c2 = queries.get(i).get(1);
            if (!parent.containsKey(c1) || !parent.containsKey(c2)) {
                //ans[idx++] = c1.equals(c2)?1.0:-1.0;
                ans[idx++] = -1.0;
            } else {
                edge p1 = findParent(c1, parent);
                edge p2 = findParent(c2, parent);
                if (!p1.node.equals(p2.node))
                    ans[idx++] = -1.0;
                else
                    ans[idx++] = p1.weight / p2.weight;
            }
        }
        return ans;
    }

    private edge findParent(String c1, HashMap<String, edge> parent) {
        edge cp = parent.get(c1);
        if (!c1.equals(cp.node)) {
            edge p = findParent(cp.node, parent);
            cp.node = p.node;
            cp.weight *= p.weight;
            parent.put(c1, cp);
        }
        return cp;
    }

    class UFS {
        HashMap<String, edge> parent = new HashMap<>();

        private edge findParent(String c1) {
            if (!parent.containsKey(c1))
                return null;
            edge n = parent.get(c1);
            if (!c1.equals(n.node)) {
                edge p = findParent(n.node);
                n.node = p.node;
                n.weight *= p.weight;
            }
            return n;
        }

        private void union(String c1, String c2, double radio) {
            boolean hasc1 = parent.containsKey(c1);
            boolean hasc2 = parent.containsKey(c2);
            if (!hasc1 && !hasc2) {
                parent.put(c1, new edge(c2, radio));
                parent.put(c2, new edge(c2, 1.0));
            } else if (!hasc1) {
                parent.put(c1, new edge(c2, radio));
            } else if (!hasc2) {
                parent.put(c2, new edge(c1, 1.0 / radio));
            } else {
                edge p1 = findParent(c1);
                edge p2 = findParent(c2);
                if (p1.node != p2.node) {
                    p1.node = p2.node;
                    p1.weight = radio * p2.weight / p1.weight;
                }
            }
        }
    }

    public double[] calcEquationUsingUFs(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, edge> parent = new HashMap<>();
        UFS ufs = new UFS();
        int n = values.length;
        for (int i = 0; i < n; i++) {
            String c1 = equations.get(i).get(0);
            String c2 = equations.get(i).get(1);
            ufs.union(c1, c2, values[i]);
        }
        int m = queries.size();
        double[] ans = new double[m];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            String c1 = queries.get(i).get(0);
            String c2 = queries.get(i).get(1);
            edge p1 = ufs.findParent(c1);
            edge p2 = ufs.findParent(c1);
            if (p1 == null || p2 == null || !p1.node.equals(p2.node))
                //ans[idx++] = c1.equals(c2)?1.0:-1.0;
                ans[idx++] = -1.0;
            else {
                ans[idx++] = p1.weight / p2.weight;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
    }
}
