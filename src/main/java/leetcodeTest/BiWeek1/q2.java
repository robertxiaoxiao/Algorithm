package leetcodeTest.BiWeek1;/*
 * @author: Robert
 * @date:  2020/2/22/022
 * @description:
 */

import java.util.HashMap;

public class q2 {
    class Cashier {
        int cnt;
        int level;
        int disct;
        HashMap<Integer, Integer> hm;

        public Cashier(int n, int discount, int[] products, int[] prices) {
            cnt = 0;
            level = n;
            disct = discount;
            hm=new HashMap<>();
            for (int i=0;i<products.length;i++)
                hm.put(products[i],prices[i]);
        }

        public double getBill(int[] product, int[] amount) {
            cnt++;
            double bill = 0;
            for (int i = 0; i < product.length; i++)
                bill += hm.get(product[i]) * amount[i];
            if (cnt == level) {
                bill = bill - (disct * bill) / 100;
                cnt = 0;
            }
            return bill;
        }
    }
}
