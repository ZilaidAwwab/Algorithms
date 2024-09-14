/*
Document search. Design an algorithm that takes a sequence of n document words and a 
sequence of m query words and find the shortest interval in which the m query words 
appear in the document in the order given. The length of an interval is the number 
of words in that interval.
*/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

public class DocumentSearch {
    public static void main(String[] args) {
        In in = new In("");
        String[] words = in.readAllStrings();

        ST<String, Queue<Integer>> windices = new ST<String, Queue<Integer>>();

        for (int i = 0; i < words.length; i++) {
            if (!windices.contains(words[i])) {
                Queue<Integer> temp = new Queue<Integer>();
                temp.enqueue(i);
                windices.put(words[i], temp);
            } else {
                Queue<Integer> temp = windices.get(words[i]);
                temp.enqueue(i);
                windices.put(words[i], temp);
            }
        }

        int bestlo = -1;
        int besthi = words.length;
        String[] query = StdIn.readAllStrings();
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[query.length];

        for (int i = 0; i < query.length; i++) {
            queues[i] = windices.get(query[i]);
        }

        Queue<Integer> starts = windices.get(query[0]);

        for (Integer start: starts) {
            boolean end = true;
            int lo = start;
            int hi = lo;

            for (int i = 0; i < query.length; i++) {
                while (!queues[i].isEmpty() && queues[i].peek() <= hi) {
                    queues[i].dequeue();
                }
                if (queues[i].isEmpty()) {
                    end = false;
                    break;
                } else {
                    hi = queues[i].peek();
                }
            }
            if (end && hi - lo < besthi - bestlo) {
                besthi = hi;
                bestlo = lo;
            }
        }
        if (bestlo >= 0) {
            int interval = besthi - bestlo;
            System.out.println("Shortest interval found: " + interval);
        } else {
            System.out.println("Not found");
        }
    }
}
