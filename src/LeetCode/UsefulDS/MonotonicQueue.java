package LeetCode.UsefulDS;

import java.util.Deque;
import java.util.LinkedList;

public class MonotonicQueue {

    Deque<Integer> queue;

    // to maintain a sliding windows at k len  to get the max num in O(n);
    public MonotonicQueue() {
        this.queue = new LinkedList<>();
    }

    public void push(int x) {
        //  if same elements exists ,better to use index
        while (!queue.isEmpty() && queue.getLast() <= x)
            queue.pollLast();
        queue.push(x);
    }

    public void pop() {
        queue.pollFirst();
    }

    public int max() {
        return queue.getFirst();
    }


}
