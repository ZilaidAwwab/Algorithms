import edu.princeton.cs.algs4.HashST;

public class SparseVector {
    private HashST<Integer, Double> st;

    public SparseVector() {
        st = new HashST<Integer, Double>();
    }

    public int size() {
        return st.size();
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!contains(i)) return 0.0;
        else return st.get(i);
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i: st.keys()) {
            sum += that[i] * this.get(i);
        }
        return sum;
    }
}

/*
 * This symbol-table client is a bare-bones sparse vector implementation that 
 * illustrates an efficient dot product for sparse vectors. We multiply each entry 
 * by its counterpart in the other operand and add the result to a running sum. The 
 * number of multiplications required is equal to the number of nonzero entries in 
 * the sparse vector. (pg: 516)
 */
