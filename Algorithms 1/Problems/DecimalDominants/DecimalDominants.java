/*
Question
Decimal dominants. Given an array with n keys, design an algorithm to find all values
that occur more than n/10 times. The expected running time of your algorithm should
be linear.
*/

import java.util.TreeMap;

import edu.princeton.cs.algs4.Bag;

public class DecimalDominants {
    private TreeMap<Integer, Integer> counts;
    private int K;
    private int N;
    private int[] A;

    public DecimalDominants(int[] a, int k) {
        A = a;
        N = a.length;
        K = k;

        buildCounts(a);
    }

    private void buildCounts(int[] a) {
        for (int i = 0; i < N; i++) {
            if (counts.containsKey(i)) counts.put(i, counts.get(i) + 1);
            else counts.put(i, 1);

            if (counts.keySet().size() >= K) removeCounts();
        }
    }

    private void removeCounts() {
        for (int K : counts.keySet()) {
            int c = counts.get(K);
            if (c > 1) counts.put(K, c-1);
            else counts.remove(K);
        }
    }

    public Iterable<Integer> find() {
        Bag<Integer> result = new Bag<Integer>();
        for (int K : counts.keySet()) {
            if (count(K) > N/K) result.add(K);
        }
        return result;
    }

    private int count(int K) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == K) count++;
        }
        return count;
    }
}
