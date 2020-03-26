package JavaCFLearning;/*
 * @author: Robert
 * @date:  2020/2/12/012
 * @description:
 */

import org.junit.Test;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMapTest {

    static class person implements Comparable<person> {
        int id;
        int score;

        public person(int id) {
            this.id = id;
            this.score = id;
        }

        public person(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(person o) {
            return this.score - o.score;
        }

        public void print() {
            System.out.printf("[id: %d  , score: %d]\r\n", id, score);
        }
    }

    public static void print(Map.Entry<Integer, person> t) {
        System.out.printf("%d  %d \r\n", t.getKey(), t.getValue().score);
    }

    public static void main(String[] args) {

        //testTreemap();
        TreeSet<person> persons = new TreeSet<>();
        for (int i = 0; i < 20; i++)
            persons.add(new person(i));

        person f = persons.floor(new person(15));
        f.print();
        person c = persons.ceiling(new person(15));
        c.print();

        for (person p : persons.subSet(new person(0), new person(15)))
            p.print();

    }

    private static void testTreemap() {
        TreeMap<Integer, person> persons = new TreeMap();
        for (int i = 0; i < 20; i++)
            persons.put(i, new person(i));
        Map.Entry f = persons.floorEntry(10);
        print(f);

        Map.Entry h = persons.higherEntry(10);
        print(h);

        Map.Entry c = persons.ceilingEntry(10);
        print(c);

        Map.Entry l = persons.lowerEntry(10);
        print(l);

        SortedMap<Integer, person> subset = persons.subMap(10, 20);

        for (int key : subset.keySet())
            System.out.println(subset.get(key).score);
    }
}
