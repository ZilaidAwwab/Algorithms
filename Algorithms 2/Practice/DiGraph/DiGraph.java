import edu.princeton.cs.algs4.Bag;

public class DiGraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public DiGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /* This is new method in the directed graphs program, all other methods are
     * identical in the graph as well
     */
    public DiGraph reverse() {
        DiGraph R = new DiGraph(V);
        for(int v = 0; v < V; v++) {
            for(int w: adj(v)) R.addEdge(w, v);
        }
        return R;
    }

    /*
     * Checkout the Graph Class and see what else methods are there, because
     * all those could be copied to this class unaltered
     */
}
