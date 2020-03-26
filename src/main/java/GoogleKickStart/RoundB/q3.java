package GoogleKickStart.RoundB;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class q3 {

    static class tnode {
        int val;
        int pres;
        int l;
        int r;

        public tnode(int val, int pres, int l, int r) {
            this.val = val;
            this.pres = pres;
            this.l = l;
            this.r = r;
        }
    }

    public static class SegmentTreeSimple {
        int N;
        int[] data;
        tnode[] tnodes;

        public SegmentTreeSimple(int[] datas) {
            int n = datas.length;
            N = n;
            data = new int[N];
            for (int i = 0; i < n; i++)
                data[i] = datas[i];
            tnodes = new tnode[N << 2];
            build(1, 0, n - 1);
        }

        public void build(int u, int l, int r) {
            if (l == r) {
                tnodes[u] = new tnode(data[l], data[l], l, r);
                return;
            }
            int mid = l + r >> 1;
            int left = u << 1;
            int right = left | 1;
            build(left, l, mid);
            build(right, mid + 1, r);
            tnodes[u] = new tnode(tnodes[left].val + tnodes[right].val, Math.max(tnodes[left].pres, tnodes[left].val + tnodes[right].pres), l, r);
        }

        public void update(int u, int k, int val) {
            int l = tnodes[u].l;
            int r = tnodes[u].r;
            if (l == r) {
                //NOTICE THERE
                tnodes[u].pres = tnodes[u].val = data[l] = val;
                return;
            }
            int mid = l + r >> 1;
            int left = u << 1;
            int right = left + 1;
            if (k <= mid)
                update(left, k, val);
            else
                update(right, k, val);

            tnodes[u].val = tnodes[left].val + tnodes[right].val;
            tnodes[u].pres = Math.max(tnodes[left].pres, tnodes[left].val + tnodes[right].pres);
        }
    }

    static int N;
    static int S;
    static int[] types;
    static HashMap<Integer, List<Integer>> indexs;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int caseNum = scanner.nextInt();
        int[] ans = new int[caseNum];

        for (int cases = 1; cases <= caseNum; cases++) {
            scanner.nextLine();
            N = scanner.nextInt();
            S = scanner.nextInt();
            scanner.nextLine();
            types = new int[N];
            indexs = new HashMap<>();
            int[] data = new int[N];
            for (int i = 0; i < N; i++) {
                int ctype = scanner.nextInt();
                types[i] = ctype;
                if (!indexs.containsKey(ctype))
                    indexs.put(ctype, new LinkedList<>());
                indexs.get(ctype).add(i);

                if (indexs.get(ctype).size() <= S)
                    data[i] = 1;
                else if (indexs.get(ctype).size() >= S + 1)
                    data[i] = -S;
            }

            SegmentTreeSimple st = new SegmentTreeSimple(data);

            int max = st.tnodes[1].pres;
            for (int j = 0; j < N; j++) {
                st.update(1, j, 0);
                //  indexs.get(types[j]).remove(0);
                int curj = lower_bound(indexs.get(types[j]), j);
                if (curj + S < indexs.get(types[j]).size())
                    st.update(1, indexs.get(types[j]).get(curj + S), 1);
                if (curj + S + 1 < indexs.get(types[j]).size())
                    st.update(1, indexs.get(types[j]).get(curj + S + 1), -S);
                max = Math.max(max, st.tnodes[1].pres);
            }

            ans[cases - 1] = max;
        }
        for (int cases = 1; cases <= caseNum; cases++) {
            System.out.printf("Case #%d: %d\n", cases, ans[cases - 1]);
        }
    }

    public static int lower_bound(List<Integer> idxs, int k) {
        int i = 0;
        int j = idxs.size() - 1;
        while (i < j) {
            int mid = i + j >> 1;
            if (idxs.get(mid) < k)
                i = mid + 1;
            else
                j = mid;
        }
        return i;
    }
}