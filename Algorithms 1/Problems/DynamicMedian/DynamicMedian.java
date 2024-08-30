/*
Question
Dynamic median
Design a data type that supports insert in logarithmic time, find-the-median in 
constant time, and remove-the-median in logarithmic time. If the number of keys in 
the data type is even, find/remove the lower median.
*/

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

public class DynamicMedian {
    private MaxPQ<Integer> left;
    private MinPQ<Integer> right;
    private int L;
    private int R;

    public DynamicMedian() {
        left = new MaxPQ<Integer>();
        right = new MinPQ<Integer>();
    }

    public double findMedian() {
        L = left.size();
        R = right.size();
        if (L == R) return ((double)left.max() + (double)right.min()) / 2;
        else if (L > R) return left.max();
        else return right.min();
    }

    public void insert(int key) {
        double median = findMedian();
        int L = left.size();
        int R = right.size();
        if (key <= median) {
            left.insert(key);
            if (L - R > 1) right.insert(left.delMax());
        } else {
            right.insert(key);
            if (R - L > 1) {
                left.insert(right.delMin());
            }
        }
    }

    public void removeMedian() {
        int L = left.size();
        int R = right.size();
        if (L > R) left.delMax();
        else right.delMin();
    }
}
