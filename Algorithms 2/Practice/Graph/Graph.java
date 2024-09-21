import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    private final int V;    // number of vertices
    private int E;          // number of edges
    private Bag<Integer>[] adj; // adjacency list
    
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];  // create array of lists

        // intialize all lists to empty
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Graph(In in) {
        this(in.readInt());     // Read V and construct this graph
        int E = in.readInt();   // Read E

        for (int i = 0; i < E; i++) {
            // add an edge
            int v = in.readInt();   // read a vertex
            int w = in.readInt();   // read another vertex
            addEdge(v, w);          // add another edge connecting them
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);      // add w to v's list
        adj[w].add(v);      // add v to w's list
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
