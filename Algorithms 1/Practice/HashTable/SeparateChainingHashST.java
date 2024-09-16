import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
    private int N;      // number of key valye pairs
    private int M;      // hash table size
    private SequentialSearchST<Key, Value>[] st;    // array of ST objects


    public SeparateChainingHashST() {
        this (1997);
    }

    public SeparateChainingHashST(int M) {
        // create M linked lists
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }
}
