import edu.princeton.cs.algs4.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;  // Array of keys
    private Value[] vals;  // Array of values
    private int N;  // Number of key-value pairs

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];  // Create array for keys
        vals = (Value[]) new Object[capacity];  // Create array for values
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        else return null;
    }

    public int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public void put(Key key, Value val) {
        /* Search for key, update value if found; grow table if new */
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;  // If key is found, update its value
            return;
        }

        // Shift keys and values to the right to insert the new key-value pair
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }

        // Insert the new key-value pair
        keys[i] = key;
        vals[i] = val;
        N++;  // Increment the size
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N - 1];
    }

    public Key select(int k) {
        return keys[k];
    }

    /* Smallest key that is greater than or equal to the given key
     * If the given key is 5, then 6 will be the ceiling considering that
     * array is from 1 to 10, if array is from 1 to 5, the 5 will be ceiling
      */
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) return null;  // All keys are less than the given key
        return keys[i];
    }

    /* Largest key that is less than or equal to the given key
     * If 5 is the key then 4 will be the floor (with the conditions mentioned above)
     */
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return keys[i];  // Exact match
        if (i == 0) return null;  // No keys less than the given key
        return keys[i - 1];  // Largest key less than the given key
    }

    public void delete(Key key) {
        if (isEmpty()) return;

        int i = rank(key);
        if (i == N || keys[i].compareTo(key) != 0) return;  // Key not found

        // Shift keys and values to the left to remove the key-value pair
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        keys[N - 1] = null;  // Avoid loitering
        vals[N - 1] = null;  // Avoid loitering
        N--;  // Decrement the size
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        
        // Include the upper bound if it is in the symbol table
        if (contains(hi)) q.enqueue(keys[rank(hi)]);
        return q;
    }
}
