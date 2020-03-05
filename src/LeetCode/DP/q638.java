package LeetCode.DP;

import java.util.ArrayList;
import java.util.List;

public class q638 {
    int min = Integer.MAX_VALUE;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return helper(price, special, needs, 0);
    }

    private int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos) {
        int local_min = directPurchase(price, needs);
        for (int i = pos; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            List<Integer> temp = new ArrayList<Integer>();
            for (int j = 0; j < needs.size(); j++) {
                if (needs.get(j) < offer.get(j)) { // check if the current offer is valid
                    temp = null;
                    break;
                }
                temp.add(needs.get(j) - offer.get(j));
            }

            if (temp != null) { // use the current offer and try next
                local_min = Math.min(local_min, offer.get(offer.size() - 1) + helper(price, special, temp, i));
            }
        }

        return local_min;
    }

    private int directPurchase(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < needs.size(); i++) {
            total += price.get(i) * needs.get(i);
        }

        return total;
    }

//    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
//        int ans = -1;
//        if (needs.size() == 0)
//            return 0;
//        for (int i = 0; i < needs.size(); i++) {
//            int cur = helper(i, price, special, needs);
//            System.out.println(cur);
//            int prep = price.get(i);
//            int pren = needs.get(i);
//            price.remove(i);
//            needs.remove(i);
//            cur += shoppingOffers(price, special, needs);
//            min = Math.min(cur, min);
//            price.add(i, prep);
//            needs.add(i, pren);
//        }
//        return min;
//    }
//    public int helper(int cur, List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
//        if (needs.get(cur) == 0)
//            return 0;
//        int pneed = needs.get(cur);
//        int pcur = price.get(cur);
//        int min = Integer.MAX_VALUE;
//        int ans = 0;
//        for (List<Integer> offer : special) {
//            int rq = offer.get(cur);
//            if (rq > pneed || rq == 0)
//                continue;
//            for (int i = 1; i <= pneed / rq; i++) {
//                List<Integer> rest = dec(offer, needs, i);
//                if (rest != null) {
//                    ans += i * offer.get(offer.size() - 1);
//                    ans += helper(cur, price, special, rest);
//                    int prest = rest.get(cur);
//                    System.out.println(prest);
//                    if (prest > 0)
//                        min += prest * pcur;
//                    needs = inc(offer, rest, i);
//                    min = Math.min(ans, min);
//                }
//            }
//        }
//        return min;
//    }
//    public List<Integer> inc(List<Integer> offer, List<Integer> rest, int time) {
//        List<Integer> ans = new LinkedList<>();
//        for (int i = 0; i < rest.size(); i++) {
//            ans.add(rest.get(i) + time * offer.get(i));
//        }
//        return ans;
//    }
//    public List<Integer> dec(List<Integer> offer, List<Integer> needs, int time) {
//
//        List<Integer> ans = new LinkedList<>();
//        for (int i = 0; i < needs.size(); i++) {
//            int rest = needs.get(i) - time * offer.get(i);
//            if (rest < 0)
//                return null;
//            ans.add(rest);
//        }
//        return ans;
//    }

}
