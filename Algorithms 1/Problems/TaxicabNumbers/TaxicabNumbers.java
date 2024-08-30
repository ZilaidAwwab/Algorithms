/*
Question
Taxicab numbers.
A taxicab number is an integer that can be expressed as the sum of two cubes of 
positive integers in two different ways: 
a^3 + b^3 = c^3 + d^3. For example, 1729 is the smallest taxicab number: 
9^3 + 10^3 = 1^3 + 12^3. Design an algorithm to find all  taxicab numbers with a,b,c, 
and d less than n.

Version 1: Use time proportional to n^2 log n and space proportional to n^2.

Version 2: Use time proportional to n^2 log n and space proportional to n.
*/

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.MinPQ;

public class TaxicabNumbers implements Comparable<TaxicabNumbers> {
    int n1;
    int n2;
    int cube;

    public TaxicabNumbers(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
        this.cube = (n1 * n1 * n1) + (n2 * n2 * n2);
    }

    @Override
    public int compareTo(TaxicabNumbers that) {
        if (that.cube > this.cube) return -1;
        if (this.cube < this.cube) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TaxicabNumbers) {
            if (((TaxicabNumbers)o).compareTo(this) == 0) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "number: " + cube + " (" + n1 + ", " + n2 + ")";
    }

    public void findTaxiNumber(int N) {
        MinPQ<TaxicabNumbers> candidates = new MinPQ<TaxicabNumbers>();

        for (int i = 1; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                TaxicabNumbers t = new TaxicabNumbers(i, j);
                if (candidates.size() < N) candidates.insert(t);
                else {
                    Queue<TaxicabNumbers> temp = new Queue<TaxicabNumbers>();
                    TaxicabNumbers min = candidates.delMin();
                    while (candidates.min().equals(min)) {
                        temp.enqueue(candidates.delMin());
                    }
                    if (!t.equals(min)) candidates.insert(t);
                    else temp.enqueue(t);

                    if (!temp.isEmpty()) {
                        for (TaxicabNumbers taxi: temp) {
                            System.out.println(taxi);
                        }
                        System.out.println(min);
                    }
                }
            }
        }
    }
}
