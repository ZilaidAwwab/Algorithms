import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
    private final int V;    // number of vertices
    private int E;          // number of edges
    private Bag<Edge>[] adj;    // adjacency list

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(In in) {
        this(in.readInt());         // Initialize the graph with V vertices
        int E = in.readInt();       // Read the number of edges
        for (int i = 0; i < E; i++) {
            int v = in.readInt();   // Read the first vertex of the edge
            int w = in.readInt();   // Read the second vertex of the edge
            double weight = in.readDouble();    // Read the weight of the edge
            Edge e = new Edge(v, w, weight);    // Create a new edge
            addEdge(e);     // Add the edge to the graph
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<>();
        for(int v = 0; v < V; v++) {
            for(Edge e: adj[v]) {
                if(e.other(v) > v) b.add(e);
            }
        }
        return b;
    }
}
